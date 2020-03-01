package com.example.gmit_sdp.beinghuman;

import android.annotation.TargetApi;
import android.app.AlertDialog;
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

public class loginbackground  extends AsyncTask<String,String,String> {

    AlertDialog alert;
    Context context;
    private Context applicationContext;
    String result,username;

    loginbackground(Context ctx)
    {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        String url1="http://dhruvesh09.000webhostapp.com/Medicare/Login.php";

        username=params[0];
        String password=params[1];

        try {
            URL url=new URL(url1);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8");

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

            bufferedreader.close();
            inputstream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (Exception e) {

        }

        return null;
    }

    protected void onPreExecute(){

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onPostExecute(String result)
    {
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        //if (result == s)
        String s=new String("LOGIN SUCCESSFULLY");
        if(result.equals(s))
        {

            Intent intent=new Intent(context,Bottom.class);
            intent.putExtra("un",username);
            context.startActivity(intent);

        }
        else
        {
            Intent intent=new Intent(context,Login.class);
            //Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            //intent.putExtra("un",username);
            context.startActivity(intent);
        }
    }
}
