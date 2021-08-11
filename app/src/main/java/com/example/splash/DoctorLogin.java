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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoctorLogin extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView forget,register;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(TextView) findViewById(R.id.register);
        forget=(TextView) findViewById(R.id.forgot);
        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorLogin.this,DoctorRegister.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorLogin.this,forgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void login(){
        String Email=email.getText().toString().trim();
        String Password=password.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Please enter email id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        //authenticate user
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(DoctorLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            FirebaseUser currentUser=mAuth.getCurrentUser();
                            if(currentUser.isEmailVerified()){
                                Log.d("Login Success", "signInWithEmail:success");
                                Toast.makeText(DoctorLogin.this,"Login Success", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DoctorLogin.this, DoctorDashboard.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(DoctorLogin.this,"Please verify your email address", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Log.d("Login Failed", "singInWithEmail:Fail");
                            Toast.makeText(DoctorLogin.this,"Login Failed", Toast.LENGTH_LONG).show();

                        }
                    }

                });
    }
}