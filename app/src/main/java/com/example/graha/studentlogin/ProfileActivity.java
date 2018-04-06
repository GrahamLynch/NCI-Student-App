package com.example.graha.studentlogin;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    NfcAdapter nfcAdapter;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        studentName = (TextView) findViewById(R.id.tvProfileName);
        studentNumber = (TextView) findViewById(R.id.tvProfileNumber);
        studentEmail = (TextView) findViewById(R.id.tvProfileEmail);

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public NdefMessage createNdefMessage() {
        String scanDate;
        String scanTime;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        month = month + 1;
        if (month < 10 && day < 10) {
            scanDate = year + "-0" + month + "-0" + day;
        } else if (month < 10) {
            scanDate = year + "-0" + month + "-" + day;
        } else if (day < 10) {
            scanDate = year + "-" + month + "-0" + day;
        } else {
            scanDate = year + "-" + month + "-" + day;
        }

        if (hour < 10 && minute < 10) {
            scanTime = "0" + hour + ":0" + minute;
        } else if (hour < 10) {
            scanTime = "0" + hour + ":" + minute;
        } else if (minute < 10) {
            scanTime = hour + ":0" + minute;
        } else {
            scanTime = hour + ":" + minute;
        }

        String studentNo = user.getEmail().substring(0, user.getEmail().indexOf('@'));

        return new NdefMessage(new NdefRecord[]{
                NdefRecord.createTextRecord("en", user.getUid()),
                NdefRecord.createTextRecord("en", studentNo),
                NdefRecord.createTextRecord("en", scanDate),
                NdefRecord.createTextRecord("en", scanTime)
                , NdefRecord.createApplicationRecord("com.example.graha.studentlogin")
        });

    }

    public void onResume() {
        nfcAdapter.setNdefPushMessage(createNdefMessage(), this);
        super.onResume();
    }


}
