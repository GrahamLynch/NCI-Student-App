package com.example.graha.studentlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Classes extends AppCompatActivity {

    //declare varaibles
    String currentDateTime;
    Button attendanceBtn;
    FirebaseAuth firebaseAuth;
    TextView scan;
    TextView lecturer;
    int counterTeamProject;
    int counterDataStructures;
    int counterNetworking;
    int counterSoftwareEngineering;
    int counterBusiness;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        //Button set to Button in XML
        attendanceBtn = (Button) findViewById(R.id.attendanceBtn);
        //Create Instance of Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //Get Day of the Week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        final String dayOfTheWeek = sdf.format(d);


        // Get Time in Hours and Minutes
        DateFormat dateFormat = new SimpleDateFormat("HMM");
        Date date = new Date();
        currentDateTime = dateFormat.format(date);
        scan = (TextView) findViewById(R.id.scanInTv);
        lecturer = (TextView) findViewById(R.id.lecturerTv);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

        //collect snapshot of Google Firebase Database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentProfile studentProfile = dataSnapshot.getValue(StudentProfile.class);
                counterTeamProject = studentProfile.getInterDisciplinaryTeamProject();
                counterNetworking = studentProfile.getDataComms();
                counterSoftwareEngineering = studentProfile.getSoftwareEngineering();
                counterBusiness = studentProfile.getBusiness();
                counterDataStructures = studentProfile.getDataStructures();
            }


            public void onCancelled(DatabaseError databaseError) {

            }

        });




        attendanceBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (dayOfTheWeek.equals("Monday") && Integer.parseInt(currentDateTime) > (900) && Integer.parseInt(currentDateTime) <= (1100)) {
                    myRef.child("interDisciplinaryTeamProject").setValue(counterTeamProject);
                    counterTeamProject++;
                    scan.setText(" You Scanned Into Interdisciplinary Team Project At : " );
                    lecturer.setText(" Your Lecturer is Frances Sheridan ");

                } else if (dayOfTheWeek.equals("Monday") && Integer.parseInt(currentDateTime) > (1300) && Integer.parseInt(currentDateTime) <= (1500)) {
                    myRef.child("dataStructures").setValue(counterDataStructures);
                    counterDataStructures++;
                    scan.setText(" You Scanned Into Data Structures At : " );
                    lecturer.setText(" Your Lecturer is Glen Ward ");

                } else if (dayOfTheWeek.equals("Tuesday") && Integer.parseInt(currentDateTime) > (1500) && Integer.parseInt(currentDateTime) <= (1700)) {
                    myRef.child("interDisciplinaryTeamProject").setValue(counterTeamProject);
                    counterTeamProject++;
                    scan.setText("You Scanned Into Interdisciplinary Team Project ");
                    lecturer.setText(" Your Lecturer is Frances Sheridan ");

                } else if (dayOfTheWeek.equals("Wednesday") && Integer.parseInt(currentDateTime) > (900) && Integer.parseInt(currentDateTime) <= (1200)) {
                    myRef.child("business").setValue(counterBusiness);
                    counterBusiness++;
                    scan.setText("You Scanned Into Business Entrepreurship ");
                    lecturer.setText(" Your Lecturer is Ron Elliot ");

                } else if (dayOfTheWeek.equals("Thursday") && Integer.parseInt(currentDateTime) > (1400) && Integer.parseInt(currentDateTime) <= (1500)) {
                    myRef.child("softwareEngineering").setValue(counterSoftwareEngineering);
                    counterSoftwareEngineering++;
                    scan.setText("You Scanned Into Software Engineering At ");
                    lecturer.setText(" Your Lecturer is Padraig De Burca ");

                } else if (dayOfTheWeek.equals("Thursday") && Integer.parseInt(currentDateTime) > (1600) && Integer.parseInt(currentDateTime) <= (1700)) {
                    myRef.child("dataComms").setValue(counterNetworking);
                    counterNetworking++;
                    scan.setText("You Scanned Into Data Communications and Networking ");
                    lecturer.setText(" Your Lecturer is Padraig De Burca ");

                } else if (dayOfTheWeek.equals("Friday") && Integer.parseInt(currentDateTime) > (900) && Integer.parseInt(currentDateTime) <= (1100)) {
                    myRef.child("interDisciplinaryTeamProject").setValue(counterTeamProject);
                    counterTeamProject++;
                    scan.setText("You Scanned Into Interdisciplinary Team Project ");
                    lecturer.setText(" Your Lecturer is Frances Sheridan ");

                } else if (dayOfTheWeek.equals("Friday") && Integer.parseInt(currentDateTime) > (1300) && Integer.parseInt(currentDateTime) <= (1500)) {
                    myRef.child("dataStructures").setValue(counterDataStructures);
                    counterDataStructures++;
                    scan.setText("You Scanned Into Data Structures At " );
                    lecturer.setText(" Your Lecturer is Glen Ward ");

                } else if (dayOfTheWeek.equals("Friday") && Integer.parseInt(currentDateTime) > (1500) && Integer.parseInt(currentDateTime) <= (1700)) {
                    myRef.child("dataComms").setValue(counterNetworking);
                    counterNetworking++;
                    scan.setText("You Scanned Into Data Communications and Networking ");
                    lecturer.setText(" Your Lecturer is Frances Sheridan ");

                } else {
                    scan.setText("You Do Not Have A Lecture At The Moment :( ");



                }


            }

        });




    }
}








