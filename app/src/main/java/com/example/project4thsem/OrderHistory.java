package com.example.project4thsem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Objects;

public class OrderHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<historyDetail> historyDetail;
    hadapter hadapter;
    FirebaseAuth fAuth;
    TextView userLogo;
    String userId;
    ImageView HomeBtn,ProfileBtn;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        recyclerView=findViewById(R.id.histRecyvle);
        userLogo=findViewById(R.id.UserLogo);
        HomeBtn=findViewById(R.id.orderHistory);
        HomeBtn.setOnClickListener(view -> {
            startActivity(new Intent(OrderHistory.this,Homepage.class));
        });
        ProfileBtn=findViewById(R.id.Profile);
        ProfileBtn.setOnClickListener(view -> {
            startActivity(new Intent(OrderHistory.this,UserProfile.class));
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyDetail=new ArrayList<historyDetail>();
        hadapter=new hadapter(OrderHistory.this,historyDetail);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    userLogo.setText(documentSnapshot.getString("Name"));

                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        recyclerView.setAdapter(hadapter);


        //Fetching OrderDetails
        fStore.collection("history")

                .whereEqualTo("userId",userId)
                .orderBy("date", Query.Direction.DESCENDING).orderBy("time", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error!=null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                historyDetail.add(dc.getDocument().toObject(historyDetail.class));
                            }
                            hadapter.notifyDataSetChanged();
                        }


                    }
                });
    }
}