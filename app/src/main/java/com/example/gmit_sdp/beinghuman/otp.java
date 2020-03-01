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

public class otp extends AppCompatActivity {

    String temp;
    protected static EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        /*Button button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                //Toast.makeText(AddressPage.this,"in button event..",Toast.LENGTH_SHORT).show();
                otp();

            }
        });*/
    }
    public void otp(View v)
    {
        /*email=(EditText)findViewById(R.id.editText8);

        if (email.getText().toString().equals(""))
        {
            Toast.makeText(otp.this,"Enter Username && Passwoed.",Toast.LENGTH_SHORT).show();
        }
        else */{
            username = (EditText) findViewById(R.id.editText13);
            final String username1 = username.getText().toString();
            String url = "http://dhruvesh09.000webhostapp.com/Medicare/otp.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {
                            temp = response;
                            //
                            if(temp.equals("Username does not exists"))
                            {
                                Intent Intent3 = new Intent(otp.this, Login.class);
                                //Intent3.putExtra("un",username1);
                                startActivity(Intent3);
                                Toast.makeText(otp.this, temp, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent Intent3 = new Intent(otp.this, ResetPassword.class);
                                Intent3.putExtra("un",username1);
                                startActivity(Intent3);
                                //Toast.makeText(otp.this, temp, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(otp.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", username1);
                    return params;
                }
            };

            //adding our stringrequest to queue
            Volley.newRequestQueue(this).add(stringRequest);

        }
    }
}
