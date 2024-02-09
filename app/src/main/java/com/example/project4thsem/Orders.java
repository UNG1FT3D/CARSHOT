package com.example.project4thsem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Orders extends AppCompatActivity {

    TextView Selected;
    String str,str2,Gold,Silver,Platinum,Basic,Advance,Premium;
    Button Home,history12,logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        Gold="Gold";
        Silver="Silver";
        Platinum="Platinum";
        Basic="Basic";
        Advance="Advance";
        Premium="Premium";

        Selected = findViewById(R.id.ordertextView);
        logout=findViewById(R.id.logout);
        Home=findViewById(R.id.Home);
        Home.setOnClickListener(view -> startActivity(new Intent(Orders.this,Homepage.class)));
        logout.setOnClickListener(view ->{
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Orders.this,Login.class));
                finish();
        });
        history12=findViewById(R.id.history12);
        history12.setOnClickListener(view -> startActivity(new Intent(Orders.this,OrderHistory.class)));

        str = getIntent().getExtras().getString("data");
        if(str.equals(Gold)){
            str2="299*";
            result(str,str2);
        }
        else if(str.equals(Silver)){
            str2="199*";
            result(str,str2);
        }
        else if(str.equals(Basic)){
            str2="499*";
            result(str,str2);
        }
        else if(str.equals(Advance)){
            str2="699*";
            result(str,str2);
        }
        else if(str.equals(Premium)){
            str2="999*";
            result(str,str2);
        }
        else {
            str2="399*";
            result(str,str2);
        }
    }
    protected  void result(String str,String str2){
        Selected.setText("Your " + str + " plan Bill is " + str2);
    }

}