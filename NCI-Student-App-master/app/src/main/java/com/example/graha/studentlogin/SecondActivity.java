package com.example.graha.studentlogin;

import android.content.Intent;
import android.media.tv.TvContract;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    ImageButton logoutBtn;
    NfcAdapter nfcAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();



        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }
    public void Logout(View view){
        firebaseAuth.signOut();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.profileMenu:
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }

    public void openReadNFC(View view){
        Intent intent = new Intent(this,ReadNFC.class);
        startActivity(intent);
    }

    //Open Activities
    public void viewPortal(View view){
        Intent intent = new Intent(this,StudentPortal.class);
        startActivity(intent);
    }

    public void viewWebsite(View view){
        Intent intent = new Intent(this,NciWebsite.class);
        startActivity(intent);
    }

    public void viewProfile(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    public void viewClasses(View view){
        Intent intent = new Intent(this,Classes.class);
        startActivity(intent);
    }

    public void viewCalendar(View view){
        Intent intent = new Intent(this,Calendar.class);
        startActivity(intent);
    }

    public void viewHome(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }





}
