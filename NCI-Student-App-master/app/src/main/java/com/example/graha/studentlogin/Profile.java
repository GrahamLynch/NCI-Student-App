package com.example.graha.studentlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser user;


    TextView studentNumberTv;
    TextView studentNameTv;
    TextView studentEmailTv;
    TextView attendanceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        studentNameTv = findViewById(R.id.studentNameTv);
        studentNumberTv = findViewById(R.id.studentNumberTv);
        studentEmailTv = findViewById(R.id.studentEmailTv);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser();

        DatabaseReference databaseReference = firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentProfile studentProfile = dataSnapshot.getValue(StudentProfile.class);
                studentNameTv.setText("Name : " + studentProfile.getStudentName());
                studentNumberTv.setText("Student Number : " + studentProfile.getStudentNumber());
                studentEmailTv.setText("Student Email : " + studentProfile.getStudentEmail());

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void viewPortal(View view){
        Intent intent = new Intent(this,StudentPortal.class);
        startActivity(intent);
    }

    public void viewHome(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }



}
