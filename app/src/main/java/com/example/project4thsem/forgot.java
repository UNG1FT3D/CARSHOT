package com.example.project4thsem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class forgot extends AppCompatActivity {
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        textView3 = (TextView) findViewById(R.id.text3);

        textView3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgot.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}