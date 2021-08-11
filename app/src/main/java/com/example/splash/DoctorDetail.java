package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetail extends AppCompatActivity {
    Button appoinment;
TextView t1,t2,t3;
String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        t1=(TextView)findViewById(R.id.name);
        t2=(TextView)findViewById(R.id.specialization);
        t3=(TextView)findViewById(R.id.experience);
        appoinment=(Button)findViewById(R.id.appointment);

        t1.setText(getIntent().getStringExtra("name"));
        t2.setText(getIntent().getStringExtra("specialization"));
        t3.setText(getIntent().getStringExtra("experience"));
        uid=getIntent().getStringExtra("uid").toString();
        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorDetail.this,MessageActivity.class);
                intent.putExtra("userid",uid);
                startActivity(intent);
            }
        });
    }
}
