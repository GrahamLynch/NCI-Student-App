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
import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.Toast;

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
    public static final String TAG = "NfcDemo";
    private TextView mTextView;
    private NfcAdapter mNfcAdapter;

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

        mTextView = (TextView) findViewById(R.id.nfcTextview);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;

        }

        if (!mNfcAdapter.isEnabled()) {
            mTextView.setText("NFC is disabled.");
        } else {
            mTextView.setText("NFC is enabled");
        }

        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        // TODO: handle Intent
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
