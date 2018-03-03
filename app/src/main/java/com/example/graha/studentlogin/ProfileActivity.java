package com.example.graha.studentlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView studentName;
    private TextView studentNumber;
    private TextView studentEmail;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        studentName = (TextView)findViewById(R.id.tvProfileName);
        studentNumber  = (TextView)findViewById(R.id.tvProfileNumber);
        studentEmail = (TextView)findViewById(R.id.tvProfileEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentProfile studentProfile = dataSnapshot.getValue(StudentProfile.class);
                studentName.setText("Name : " + studentProfile.getStudentName());
                studentNumber.setText("Student Number : " + studentProfile.getStudentNumber());
                studentEmail.setText("Student Email : " + studentProfile.getStudentEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
