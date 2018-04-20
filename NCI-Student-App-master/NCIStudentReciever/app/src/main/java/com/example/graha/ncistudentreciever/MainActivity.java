package com.example.graha.ncistudentreciever;
//package com.example.graha.studentlogin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graha.ncistudentreciever.Attendance;
import com.example.graha.ncistudentreciever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity  {

    NfcAdapter nfcAdapter;
    private List<Attendance> attendanceList;
    String moduleName, sessionDate, startTime,endTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

    }



    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }


    void processIntent(Intent intent) {
        Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if(parcelables != null){
            NdefMessage[] messages = new NdefMessage[parcelables.length];
            for(int i = 0; i< parcelables.length; i++){
                messages[i] = (NdefMessage) parcelables[i];
            }

            NdefMessage message =  messages[0];
            int index = 3;



           String uid = new String(message.getRecords()[0].getPayload());
            uid = uid.substring(index, uid.length());

            String studentNo = new String(message.getRecords()[1].getPayload());
            studentNo = studentNo.substring(index, studentNo.length());



        }
        else{
            Toast.makeText(this, "NFC UnSuccessful", Toast.LENGTH_SHORT).show();
        }
    }


}


