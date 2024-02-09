package com.example.project4thsem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {
    ImageView Home,OrderHistory;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView email,phonenumber,name;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        email=findViewById(R.id.email);

        Home=findViewById(R.id.Home);
        Home.setOnClickListener(view -> {startActivity(new Intent(this,Homepage.class));});
        OrderHistory=findViewById(R.id.History);
        OrderHistory.setOnClickListener(view -> {startActivity(new Intent(this,OrderHistory.class));});

        phonenumber=findViewById(R.id.phonenumber);
        name=findViewById(R.id.name);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, (documentSnapshot, e) -> {

                phonenumber.setText(documentSnapshot.getString("phone"));
                name.setText(documentSnapshot.getString("Name"));
                email.setText(documentSnapshot.getString("email"));


        });
        //LogoutBtn=findViewById(R.id.Logout);
        //LogoutBtn.setOnClickListener(view ->{

            //startActivity(new Intent(UserProfile.this,Homepage.class));

        //});


    }

}