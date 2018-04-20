package com.example.graha.studentlogin;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;

public class ReadNFC extends AppCompatActivity {
    NfcAdapter nfcAdapter;
    TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_nfc);



//        //NFC Declarations and Options Etc..
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        if (nfcAdapter == null) {
//            Toast.makeText(this, "NO NFC ON DEVICE", Toast.LENGTH_SHORT).show();
//        } else if (!nfcAdapter.isEnabled()) {
//            Toast.makeText(this,"Please enable NFC!",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
//        } else if (!nfcAdapter.isNdefPushEnabled()) {
//            Toast.makeText(this,"Please enable Android Beam",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
//        }
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

            System.out.println(messages.toString());


        }
        else{
            Toast.makeText(this, "NFC UnSuccessful", Toast.LENGTH_SHORT).show();
        }
    }


}


