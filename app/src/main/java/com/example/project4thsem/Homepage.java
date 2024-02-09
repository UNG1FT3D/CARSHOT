package com.example.project4thsem;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Objects;

public class Homepage extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btnLogout;
    TextView userLogo;
    ImageButton imgBtn1,imgBtn2;
    String userId;
    ImageView OrderHistoryBtn,ProfileBtn;
    FirebaseFirestore fStore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        userLogo=findViewById(R.id.UserLogo);
        btnLogout=findViewById(R.id.logout);
        btnLogout.setOnClickListener(view ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Homepage.this,Login.class));
            finish();
        });
        OrderHistoryBtn=findViewById(R.id.orderHistory);
        OrderHistoryBtn.setOnClickListener(view -> startActivity(new Intent(Homepage.this,OrderHistory.class)));
        ProfileBtn=findViewById(R.id.Profile);
        ProfileBtn.setOnClickListener(view -> startActivity(new Intent(Homepage.this,UserProfile.class)));

        imgBtn1=findViewById(R.id.CarWash);
        imgBtn2=findViewById(R.id.CarService);
        //mAuth = FirebaseAuth.getInstance();
        //fStore = FirebaseFirestore.getInstance();
        //userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        //DocumentReference documentReference = fStore.collection("users").document(userId);
        //documentReference.addSnapshotListener(this, (documentSnapshot, e) -> {
         //   if(documentSnapshot.exists()){
          //      userLogo.setText(documentSnapshot.getString("Name"));
          //  }else {
           //     Log.d("tag", "onEvent: Document do not exists");
           // }
        //});

        imgBtn1.setOnClickListener(view -> startActivity(new Intent(Homepage.this,Carwash.class)));
        imgBtn2.setOnClickListener(view -> startActivity(new Intent(Homepage.this,Carservice.class)));

    }

//    public void logout(View view) {
//        FirebaseAuth.getInstance().signOut();//logout
//        startActivity(new Intent(getApplicationContext(), Login.class));
//        finish();
//    }
}