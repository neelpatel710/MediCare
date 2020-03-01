package com.example.gmit_sdp.beinghuman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private static EditText username,forgot;
    private static EditText password;

    private static Button button_login;
    int attempts_counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void otp1(View v)
    {
        Intent i = new Intent(Login.this,otp.class);//,ResetPassword.class);
        startActivity(i);
    }
    public void Login_Button(View v) {
        username = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);

        button_login = (Button) findViewById(R.id.button_login);

        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
        /*Backgroundworker backgroundworker= new Backgroundworker(this);
        backgroundworker.execute(username1,password1);*/

        if (username.getText().toString().equals("") && password.getText().toString().equals("")) {
            Toast.makeText(Login.this, "Enter Username && Passwoed.", Toast.LENGTH_SHORT).show();
        } else if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            if (username.getText().toString().equals("")) {
                Toast.makeText(Login.this, "Enter Username.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Login.this, "Enter Password.", Toast.LENGTH_SHORT).show();
            }
            attempts_counter--;
            if (attempts_counter == 0) {
                button_login.setEnabled(false);
            }
        } else if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin1234")) {
            /*Toast.makeText(LoginPage.this,"Username and Password is Correct.",Toast.LENGTH_SHORT).show();
            Intent Intent2=new Intent(getApplicationContext(),User.class);
            startActivity(Intent2);*/
        } else {
            loginbackground backgroundworker = new loginbackground(this);
            backgroundworker.execute(username1, password1);
            //editor.putBoolean("key_name", true);
            /*if(!isUserLogin) {
                Intent Intent2 = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(Intent2);
            }
            if(isUserLogin)
            {
                Intent Intent2 = new Intent(getApplicationContext(), User.class);
                startActivity(Intent2);
            }*/
            //editor.commit();
            attempts_counter--;
            if (attempts_counter == 0) {
                button_login.setEnabled(false);
            }
        }
    }
    public void register(View v)
    {
        Intent i=new Intent(Login.this,Signup.class);
        startActivity(i);
    }
}
