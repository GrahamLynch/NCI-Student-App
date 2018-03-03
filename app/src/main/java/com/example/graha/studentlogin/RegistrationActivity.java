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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText studentNumber, studentPassword, studentEmail, studentName;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String student_number;
    String student_password;
    String student_email;
    String student_name;

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
        studentName = (EditText)findViewById(R.id.nameText);
    }

    private Boolean validate(){
        Boolean result = false;

        student_number = studentNumber.getText().toString();
        student_password = studentPassword.getText().toString();
        student_email = studentEmail.getText().toString();
        student_name = studentName.getText().toString();

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
                        sendUserData();
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

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StudentProfile studentProfile = new StudentProfile(student_name , student_number , student_email);
        myRef.setValue(studentProfile);
    }


}
