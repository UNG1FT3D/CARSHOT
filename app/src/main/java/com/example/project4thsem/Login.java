package com.example.project4thsem;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText etLoginEmail;
    EditText etLoginPassword;
    TextView  RegisterNow;
    TextView  forgotPass;
    Button btnLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLoginEmail=findViewById(R.id.Username);
        etLoginPassword=findViewById(R.id.Password);
        RegisterNow=findViewById(R.id.register);
        forgotPass=findViewById(R.id.forgot);

        btnLogin=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),Homepage.class));
            finish();
        }
        btnLogin.setOnClickListener(view -> loginUser());
        RegisterNow.setOnClickListener(view -> {
            Intent intent=new Intent(Login.this,Signup.class);
            startActivity(intent);
        });
        forgotPass.setOnClickListener(view -> {
            Intent intent=new Intent(Login.this,forgot.class);
            startActivity(intent);
        });



    }
    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email Cannot be empty");
            etLoginEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){


                    Toast.makeText(Login.this,"User Logged in successfully",Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(Login.this,Homepage.class));
                }else{
                    Toast.makeText(Login.this,"Login error",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}