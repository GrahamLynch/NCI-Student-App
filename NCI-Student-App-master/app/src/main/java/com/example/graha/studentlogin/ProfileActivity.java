package com.example.graha.studentlogin;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private TextView studentName;
    private TextView studentNumber;
    private TextView studentEmail;
    private TextView attendance1;
    private TextView teamProject1;
    private TextView dataStructures1;
    private TextView dataComms1;
    private TextView softwareEngineering1;
    private TextView business1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAnalytics mFirebaseAnalytics;
    NfcAdapter nfcAdapter;

    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        studentName = (TextView) findViewById(R.id.studentNameTv);
        studentNumber = (TextView) findViewById(R.id.studentNumberTv);
        studentEmail = (TextView) findViewById(R.id.studentEmailTv);
        teamProject1 = (TextView) findViewById(R.id.teamProjectTv);
        dataStructures1 = (TextView) findViewById(R.id.dataStructuresTv);
        dataComms1 = (TextView) findViewById(R.id.dataCommsTv);
        softwareEngineering1 = (TextView) findViewById(R.id.softwareEngineeringTv);
        business1 = (TextView) findViewById(R.id.businessTv);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser();



        DatabaseReference databaseReference = firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentProfile studentProfile = dataSnapshot.getValue(StudentProfile.class);
                studentName.setText("Name : " + studentProfile.getStudentName());
                studentNumber.setText("Student Number : " + studentProfile.getStudentNumber());
                studentEmail.setText("Student Email : " + studentProfile.getStudentEmail());
                teamProject1.setText("Interdisciplinary Team Project Attendance This Week : " + studentProfile.getInterDisciplinaryTeamProject());
                dataStructures1.setText("Data Structures Attendance This Week : " +studentProfile.getDataStructures());
                dataComms1.setText("Data Communications & Networking Attendance This Week : " +studentProfile.getDataComms());
                softwareEngineering1.setText("Software Engineering Attendance This Week : " +studentProfile.getSoftwareEngineering());
                business1.setText("Business Entrepreneurship Attendance This Week : " +studentProfile.getBusiness());


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public NdefMessage createNdefMessage() {


        String studentNo = user.getEmail().substring(0, user.getEmail().indexOf('@'));

        return new NdefMessage(new NdefRecord[]{

                NdefRecord.createTextRecord("en", user.getUid()),
                NdefRecord.createTextRecord("en", studentNo),
                NdefRecord.createApplicationRecord("com.example.graha.ncistudentreciever")
        });

    }

    public void viewHome(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    public void viewPortal(View view){
        Intent intent = new Intent(this,StudentPortal.class);
        startActivity(intent);
    }

//    public void onResume() {
//        nfcAdapter.setNdefPushMessage(createNdefMessage(), this);
//        super.onResume();
//    }


}
