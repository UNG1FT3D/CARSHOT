package com.example.project4thsem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText etRegEmail,mFullName,mPassword,mPhone;
    Button btnRegistar,RLogin;
    FirebaseAuth fAuth;
    String userID;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFullName = findViewById(R.id.Username);
        etRegEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.Password);
        mPhone = findViewById(R.id.phone);
        btnRegistar = findViewById(R.id.register);

        fAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),Homepage.class));
            finish();
        }
        RLogin=findViewById(R.id.login);
        RLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String email = etRegEmail.getText().toString();
            String password = mPassword.getText().toString();
            String name = mFullName.getText().toString();
            String phone = mPhone.getText().toString();

            if (TextUtils.isEmpty(email)){
                etRegEmail.setError("Email Cannot be empty");
                etRegEmail.requestFocus();
            }
            if (TextUtils.isEmpty(password)){
                mPassword.setError("Password cannot be empty");
                mPassword.requestFocus();
            }
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "User registered successfully",Toast.LENGTH_SHORT).show();
                        userID= fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference=fStore.collection("users").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Name",name);
                        user.put("email",email);
                        user.put("phone",phone);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: user Profile is Created for "+userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: "+e.toString());
                            }
                        });

                        startActivity(new Intent(Signup.this,Homepage.class));
                    }else{
                        Toast.makeText(Signup.this,"Error Occured",Toast.LENGTH_SHORT).show();
                    }
                }
                });
            }
        });

    }}