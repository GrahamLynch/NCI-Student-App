package com.example.graha.studentlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Student;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView studentRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student = (EditText) findViewById(R.id.etStudent);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.attemptView);
        Login = (Button) findViewById(R.id.logInBtn);
        studentRegistration = (TextView)findViewById(R.id.textViewRegister);
        forgotPassword = (TextView)findViewById(R.id.textViewForgotPassword);

        Info.setText(" No of attempts remaining 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(MainActivity.this, "You Need NFC to use This App", Toast.LENGTH_SHORT).show();
            finish(); }

         else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(MainActivity.this, "Please Enable NFC", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        } else if (!nfcAdapter.isNdefPushEnabled()) {
            Toast.makeText(MainActivity.this, "Please Enable Androidn Beam", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Student.getText().toString(), Password.getText().toString());
            }
        });

        studentRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });
    }


    private void validate(String studentNumber, String password) {

        progressDialog.setMessage("Logging into your Student Account");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(studentNumber, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Student Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }else{
                    Toast.makeText(MainActivity.this, "Student Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of attempts remaining" + counter);
                    progressDialog.dismiss();
                    if (counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }else{
            Toast.makeText(this, "Verify your Student Email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
