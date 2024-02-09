package com.example.project4thsem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Carservice extends AppCompatActivity {

    Button proceedBtn;
    RadioButton selectedBtn;
    RadioGroup radiogroup;
    TextView planselected;
    EditText edittext1;
    ImageView Home,Profile;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carservice);

       // imgBtnBack=findViewById(R.id.moveback);
        radiogroup=findViewById(R.id.radiogroup);
        proceedBtn=findViewById(R.id.Proceed);
        Home=findViewById(R.id.Home);
        Profile=findViewById(R.id.Profile);
        planselected=findViewById(R.id.UserLogo);
        edittext1=findViewById(R.id.Edittext);


        Home.setOnClickListener(view -> { startActivity(new Intent(Carservice.this,Homepage.class));
        });
       Profile.setOnClickListener(view -> { startActivity(new Intent(Carservice.this,UserProfile.class));
        });
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, (documentSnapshot, e) -> {
            assert documentSnapshot != null;
            if (!documentSnapshot.exists()) {
                Log.d("tag", "onEvent: Document do not exists");
            } else {
                planselected.setText(documentSnapshot.getString("Name"));

            }
        });


        proceedBtn.setOnClickListener(view -> {
            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            String type = "Carservice";
            selectedBtn = radiogroup.findViewById(radiogroup.getCheckedRadioButtonId());
            if (selectedBtn != null) {
                //Toast.makeText(Carwash.this, "You Have Choosed:"+selectedBtn.getText()+" Plan", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Carservice.this,Orders.class);
                String str1=selectedBtn.getText().toString();
                edittext1.setText("You have Choose "+str1+" Plan");
                planselected.setText(str1);
                Map<String,Object> dbsbk = new HashMap<>();
                dbsbk.put("userId",userId);
                dbsbk.put("type",type);
                dbsbk.put("plan",str1);
                dbsbk.put("date",currentDate);
                dbsbk.put("time",currentTime);

                fStore.collection("history").add(dbsbk)
                        .addOnCompleteListener(task -> {
                            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(Carservice.this,Orders.class);
                            intent1.putExtra("data",str1);
                            startActivity(intent1);
                        });
            }
            else {
                Toast.makeText(Carservice.this,"Choose At least One Plan",Toast.LENGTH_SHORT).show();
            }

        });
    }


}