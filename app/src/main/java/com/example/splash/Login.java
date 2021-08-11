package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
    Button patient,doctor;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        doctor=findViewById(R.id.doctor);
        patient=findViewById(R.id.patient);



        mAuth=FirebaseAuth.getInstance();
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,DoctorLogin.class);
                startActivity(intent);
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,PatientLogin.class);
                startActivity(intent);
            }
        });




    }

  // @Override
    //protected void onStart() {
      //  super.onStart();
        //FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        //if(user!=null){
          //  Intent intent=new Intent(Login.this,PatientDashboard.class);
            //startActivity(intent);
        //}
    //}

}