package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegister extends AppCompatActivity {
    Button button1;
    TextView textView;
    EditText fname, special, email,  password,experience;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference node;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);
        fname = (EditText) findViewById(R.id.fname);
        special = (EditText) findViewById(R.id.special);
        email = (EditText) findViewById(R.id.femail);
        password = (EditText) findViewById(R.id.password);
        experience = (EditText) findViewById(R.id.experience);
        button1 = findViewById(R.id.submit);
        textView = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        node=db.getReference("DoctorsRegistrationDetail");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorRegister.this, DoctorLogin.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid();
            }
        });
    }
    private boolean validateName(){
        String val=fname.getText().toString().trim();
        if(val.isEmpty()){
            fname.setError("field can not be empty");
            return false;
        }else{

            return true;

        }
    }
    private boolean validateSpecialization(){
        String val=special.getText().toString().trim();
        if(val.isEmpty()){
            special.setError("field can not be empty");
            return false;
        }else{

            return true;

        }

    }
    private boolean validateExperience(){
        String val=experience.getText().toString().trim();
        if(val.isEmpty()){
            experience.setError("field can not be empty");
            return false;
        }else{

            return true;

        }

    }
    private boolean validateEmail(){
        String val=email.getText().toString().trim();
        String checkEmail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            email.setError("field can not be empty");
            return false;
        }else if(!val.matches(checkEmail)){
            email.setError("Invalid Email");
            return false;
        }
        else{
            return true;
        }

    }
    private boolean validatePassword(){
        String val=password.getText().toString().trim();
        String checkPassword="^" +
                //"(?=.*[0-9])" +
                //"(?=.*[A-Z])" +
                //"(?=.*[a-z])" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";

        if(val.isEmpty()){
            password.setError("field can not be empty");
            return false;
        }else if(!val.matches(checkPassword)){
            password.setError("Invalid Password");
            return false;
        }
        else{

            return true;
        }

    }
    public void valid() {
        Log.d("inside", "validate: inside valid  ");
        if(!validateName() || !validateExperience() || !validateEmail() || !validatePassword() || !validateSpecialization()){
            return;
        }
        else {
            String Email=email.getText().toString().trim();
            String Password=password.getText().toString().trim();
            String Name=fname.getText().toString().trim();
            String Specialization=special.getText().toString().trim();
            String Experience=experience.getText().toString().trim();


            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mAuth.getCurrentUser().sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    RegistrationDetail registrationDetail=new RegistrationDetail(mAuth.getCurrentUser().getUid(),Name,Specialization,Email, Password,Experience);
                                    node=db.getReference("RegistrationDetail");
                                    node.child(mAuth.getCurrentUser().getUid()).setValue(registrationDetail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Log.d("SignUpSuccess", "Registration:success");
                                            Toast.makeText(DoctorRegister.this,"Registration Successful! Please visit your Email address to verify your email address.",Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(DoctorRegister.this,DoctorLogin.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                                }
                                else{
                                    Toast.makeText(DoctorRegister.this,"Email can not be send !",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("SignUpFailed", "Registration:failure", task.getException());
                        Toast.makeText(DoctorRegister.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

}