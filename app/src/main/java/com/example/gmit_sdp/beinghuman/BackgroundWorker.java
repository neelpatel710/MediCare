package com.example.gmit_sdp.beinghuman;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,String,String> {

    Context context;

    String result;
    BackgroundWorker (Context ctx)
    {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String url1="http://dhruvesh09.000webhostapp.com/Medicare/user_details.php";
        String firstname=params[0];
        String middlename=params[1];
        String lastname=params[2];
        String email=params[3];
        String mobileno=params[4];
        String username=params[5];
        String password=params[6];
        String gender=params[7];

        try {
            URL url=new URL(url1);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("firstname","UTF-8")+"="+ URLEncoder.encode(firstname,"UTF-8")+"&"+URLEncoder.encode("middlename","UTF-8")+"="+
                    URLEncoder.encode(middlename,"UTF-8")+"&"+URLEncoder.encode("lastname","UTF-8")+"="+ URLEncoder.encode(lastname,"UTF-8")+"&"+
                    URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("mobileno","UTF-8")+"="+ URLEncoder.encode(mobileno,"UTF-8")+"&"+
                    URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8")
                    +"&"+URLEncoder.encode("gender","UTF-8")+"="+ URLEncoder.encode(gender,"UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            InputStream inputstream=httpURLConnection.getInputStream();
            BufferedReader bufferedreader=new BufferedReader(new InputStreamReader(inputstream,"UTF-8"));
            result="";
            String line;
            while ((line=bufferedreader.readLine())!=null){
                result=result+line;
            }
            return result;
        } catch (Exception e) {

        }

        return null;
    }

    protected void onPreExecute(){

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onPostExecute(String result){
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        String s=new String("SIGN UP SUCCSSESULLY");
        if(result.equals(s))
        {

            Intent intent=new Intent(context,Login.class);
           // intent.putExtra("sign","sign");
            context.startActivity(intent);
            /*alert.setMessage(result);
            alert.show();*/

        }
        else
        {
            Intent intent=new Intent(context,Signup.class);
            context.startActivity(intent);
            /*alert.setMessage(result);
            alert.show();*/
        }
    }


}
