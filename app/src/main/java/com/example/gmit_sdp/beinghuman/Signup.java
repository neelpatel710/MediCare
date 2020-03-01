package com.example.gmit_sdp.beinghuman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.lang.*;

public class Signup extends AppCompatActivity {
    protected static EditText username,password,firstname,middlename,lastname,email;
    protected static EditText mobileno;
    protected static RadioButton male,female,other;
    String gender="Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void radio(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.Male:
                if(checked){
                    gender="Male";}
                break;
            case R.id.Female:
                if(checked){
                    gender="Female";}
                break;

            case R.id.Other:
                if(checked){
                    gender="Other";}
                break;

        }

    }

    public void goto3(View v)
    {

        username=(EditText)findViewById(R.id.editText6);
        password=(EditText)findViewById(R.id.editText7);
        email=(EditText)findViewById(R.id.editText8);
        firstname=(EditText)findViewById(R.id.editText2);
        lastname=(EditText)findViewById(R.id.editText);
        middlename=(EditText)findViewById(R.id.editText3);
        mobileno=(EditText)findViewById(R.id.editText9);


        String firstname1,lastname1,middlename1,username1,password1,email1,mobileno1,gender1;
        firstname1=firstname.getText().toString();
        lastname1=lastname.getText().toString();
        middlename1=middlename.getText().toString();
        username1=username.getText().toString();
        password1=password.getText().toString();
        mobileno1=mobileno.getText().toString();
        email1=email.getText().toString();
        gender1=gender.toString();



        if(username.getText().toString().equals(" ") || password.getText().toString().equals(" ") || firstname.getText().toString().equals(" ") || lastname.getText().toString().equals(" ") || middlename.getText().toString().equals("") || email.getText().toString().equals("") || mobileno.getText().toString().equals("") )
        {
            Toast.makeText(Signup.this,"Enter all Details Properly.",Toast.LENGTH_SHORT).show();
        }
        else if(mobileno.getText().toString().length()<10 || mobileno.getText().toString().length()>10)
        {
            Toast.makeText(Signup.this,"Enter Valid Mobile Number.",Toast.LENGTH_SHORT).show();
        }
        else {
           /* BackgroundWorker backgroundworker= new BackgroundWorker(this);
            backgroundworker.execute(firstname1,middlename1,lastname1,email1,mobileno1,username1,password1,gender1);*/

            Intent Intent3 = new Intent(getApplicationContext(), MainActivity.class);
            Intent3.putExtra("firstname",firstname1);
            Intent3.putExtra("middlename",middlename1);
            Intent3.putExtra("lastname",lastname1);
            Intent3.putExtra("username",username1);
            Intent3.putExtra("password",password1);
            Intent3.putExtra("mobile",mobileno1);
            Intent3.putExtra("email",email1);
            Intent3.putExtra("gender",gender1);
            startActivity(Intent3);
        }
    }
}
