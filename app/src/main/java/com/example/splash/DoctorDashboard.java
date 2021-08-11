package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorDashboard extends AppCompatActivity {
Button logout;
FloatingActionButton floatingActionButton,floatingActionButton2;
RecyclerView recview;
Blog adapter;
FirebaseUser fuser;
DatabaseReference reference;
ArrayList<TextModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        logout=(Button)findViewById(R.id.logout);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton2=findViewById(R.id.floatingActionAdd);
        recview = (RecyclerView) findViewById(R.id.recview);
        fuser=FirebaseAuth.getInstance().getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference("Blogs").child(fuser.getUid());

        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDashboard.this,DoctorLogin.class));
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivity(new Intent(DoctorDashboard.this,ChatActivity2.class));
           }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDashboard.this,SimpleText.class));
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