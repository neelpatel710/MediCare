package com.example.gmit_sdp.beinghuman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ResetPassword extends AppCompatActivity {

    protected static EditText username,otp,pwd,repwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);



    }
    protected void goto9(View view)
    {
        pwd = (EditText) findViewById(R.id.editText13);
        final String pwd1 = pwd.getText().toString();
        repwd = (EditText) findViewById(R.id.editText14);
        final String repwd1 = repwd.getText().toString();
        otp = (EditText) findViewById(R.id.editText15);
        final String otp1 = otp.getText().toString();
        //username = (EditText) findViewById(R.id.editText16);
        final String username1=getIntent().getExtras().getString("un");
        //final String value= getIntent().getExtras().getString("potp");
        //Toast.makeText(ResetPassword.this,"OTP Passed: "+value,Toast.LENGTH_SHORT).show();
        //username.getText().toString().equals("") ||
        if (otp.getText().toString().equals("") ||  pwd.getText().toString().equals("") || repwd.getText().toString().equals(""))
        {
            Toast.makeText(ResetPassword.this,"Enter all details correctly.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            /*pwd = (EditText) findViewById(R.id.editText13);
            final String pwd1 = pwd.getText().toString();
            repwd = (EditText) findViewById(R.id.editText14);
            final String repwd1 = pwd.getText().toString();
            otp = (EditText) findViewById(R.id.editText15);
            final String otp1 = pwd.getText().toString();
            username = (EditText) findViewById(R.id.editText16);
            final String username1 = pwd.getText().toString();*/

            String url = "http://dhruvesh09.000webhostapp.com/Medicare/reset.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ResetPassword.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ResetPassword.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username1);
                    params.put("otp", otp1);
                    params.put("pwd", pwd1);
                    params.put("repwd", repwd1);
                    //params.put("rotp", username1);
                    return params;
                }
            };

            //adding our stringrequest to queue
            Volley.newRequestQueue(this).add(stringRequest);


        }
    }
}
