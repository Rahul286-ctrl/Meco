package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgotPassword extends AppCompatActivity {
TextView textView;
EditText email;
Button send;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        textView=findViewById(R.id.ftext);
        email=findViewById(R.id.femail);
        send=findViewById(R.id.fbutton);
        mAuth=FirebaseAuth.getInstance();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                send.setEnabled(false);
                email.setEnabled(false);
                mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            send.setEnabled(true);
                            email.setEnabled(true);
                            Toast.makeText(forgotPassword.this, "Reset password link has been sent to the registered email.", Toast.LENGTH_LONG).show();

                        finish();
                        }
                        else {
                            Toast.makeText(forgotPassword.this, "Reset pasword link can not be sent to the registered email.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}