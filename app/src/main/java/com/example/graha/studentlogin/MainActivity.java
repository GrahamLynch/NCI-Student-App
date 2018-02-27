package com.example.graha.studentlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Student;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView studentRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student = (EditText) findViewById(R.id.etStudent);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.attemptView);
        Login = (Button) findViewById(R.id.logInBtn);
        studentRegistration = (TextView)findViewById(R.id.textViewRegister);

        Info.setText(" No of attempts remaining 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Student.getText().toString(), Password.getText().toString());
            }
        });

        studentRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class
                ));
            }
        });
    }

    private void validate(String studentNumber, String password) {
        if ((studentNumber.equals("x16120132")) && (password.equals("1234"))){

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else {
            counter--;

            Info.setText("No of attempts remaining" + String.valueOf(counter));

            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }
}
