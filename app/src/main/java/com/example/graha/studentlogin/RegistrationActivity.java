package com.example.graha.studentlogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegistrationActivity extends AppCompatActivity {

    private EditText studentNumber, studentPassword, studentEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth =  FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Register data to database
                    //String student_number = studentNumber.getText().toString().trim();
                    String student_email  = studentEmail.getText().toString().trim();
                    String student_password = studentPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(student_email, student_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                            }else{
                                Toast.makeText(RegistrationActivity.this,  "Student Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        studentNumber = (EditText)findViewById(R.id.studentNumberPt);
        studentPassword = (EditText)findViewById(R.id.etUserPassword);
        studentEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton  = (Button)findViewById(R.id.registerBtn);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
    }

    private Boolean validate(){
        Boolean result = false;

        String student_number = studentNumber.getText().toString();
        String student_password = studentPassword.getText().toString();
        String student_email = studentEmail.getText().toString();

        if(student_number.isEmpty() || student_password.isEmpty() || student_email.isEmpty()){
            Toast.makeText(this, "Please Enter All of Your Information", Toast.LENGTH_SHORT).show();
        }else{
            result = true ;
        }

        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "You have been successfully registered, Verification Email has been sent to your student Email Address!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this,"Verification Email has not been sent to your Student Email",Toast.LENGTH_SHORT ).show();
                    }
                }
            });
        }
    }


}
