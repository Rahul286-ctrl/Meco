package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientDashboard extends AppCompatActivity {
   FloatingActionButton floatingActionButton1,floatingActionButton2;
    RecyclerView recview;
    Blog adapter;
    FirebaseUser fuser;
    DatabaseReference reference;
    ArrayList<TextModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);
        floatingActionButton1=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton2=(FloatingActionButton)findViewById(R.id.floatingActionMessage);

        recview = (RecyclerView) findViewById(R.id.recview);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference().child("Blogs");

        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDashboard.this,Dashboard.class));
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDashboard.this,ChatActivity.class));
            }
        });

        list=new ArrayList<>();
        adapter=new Blog(this,list);
        recview.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TextModel blog = dataSnapshot.getValue(TextModel.class);
                    list.add(blog);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}