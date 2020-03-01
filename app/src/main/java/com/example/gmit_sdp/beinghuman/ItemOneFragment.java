package com.example.gmit_sdp.beinghuman;


/**
 * Created by DELL on 02-08-2017.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

public class ItemOneFragment extends Fragment {
   public String post_data;
    Button btnalarm,lbtn,dbtn;
    /*AlarmManager manager;
    PendingIntent pn;*/
    String json_url2;
    EditText editText5;
    String j_string;
    int count = 0,i,j=0,h,h1,h2,m,m1,m2;
    int bflag=0,lflag=0,dflag=0;
    int[] d=new int[3];
    String medicine;
    int breakfast,lunch,dinner;
    View v;

    String bdemo = "",ldemo="",ddemo="";
    Intent openclock = new Intent(AlarmClock.ACTION_SET_ALARM);
    Intent openclock1 = new Intent(AlarmClock.ACTION_SET_ALARM);
    Intent openclock2 = new Intent(AlarmClock.ACTION_SET_ALARM);
    //Intent lunch1=new Intent(getApplicationContext(),lunch1.class);
    //Intent[] openclock= new Intent[3];
    String un;
    ArrayList<Integer> alarmDays= new ArrayList<Integer>();

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_item_one, container, false);
      // String number= f.getNumber().toString();
       // x =c.getNumbers();
        btnalarm = v.findViewById(R.id.btnalarm);
        lbtn=v.findViewById(R.id.button3);
        dbtn=v.findViewById(R.id.button4);
        btnalarm.setOnClickListener(mButtonClickListener);
        dbtn.setOnClickListener(dbutton);
        lbtn.setOnClickListener(lbutton);
        un = getActivity().getIntent().getExtras().getString("un");
        new BackgroundWork1().execute();

        return v;
    }
    private View.OnClickListener mButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            bgo(v);
        }
    };
    private View.OnClickListener dbutton = new View.OnClickListener() {
        public void onClick(View v) {
            dgo(v);
        }
    };
    private View.OnClickListener lbutton = new View.OnClickListener() {
        public void onClick(View v) {
            lgo(v);
        }
    };

    public class BackgroundWork1 extends AsyncTask<String, Void, String> {

        String json_url;
        String json_string;

        @Override
        protected void onPreExecute() {
            json_url = "http://dhruvesh09.000webhostapp.com/Medicare/clock.php";

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                 post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            j_string = s;
            try {
                jsontask(j_string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void jsontask(String s) throws JSONException {


        JSONObject jsonObject = new JSONObject(s);
        JSONArray jsonArray = jsonObject.getJSONArray("response");

        alarmDays.add(Calendar.SUNDAY);
        alarmDays.add(Calendar.MONDAY);
        alarmDays.add(Calendar.TUESDAY);
        alarmDays.add(Calendar.WEDNESDAY);
        alarmDays.add(Calendar.THURSDAY);
        alarmDays.add(Calendar.FRIDAY);
        alarmDays.add(Calendar.SATURDAY);



        while (count < jsonArray.length())
        {
            JSONObject jo = jsonArray.getJSONObject(count);

            breakfast=Integer.parseInt(jo.getString("breakfast"));
            lunch=Integer.parseInt(jo.getString("lunch"));
            dinner=Integer.parseInt(jo.getString("dinner"));
            medicine=jo.getString("medicine");



            if (breakfast == 1) {

                bdemo = bdemo + medicine+",";
                bflag=1;

            }
            if (lunch == 1) {

                ldemo = ldemo +medicine+",";
                lflag=1;
            }

            if (dinner == 1) {

                dflag=1;
                ddemo = ddemo + medicine+",";
            }

            count++;
        }
        if(bflag==1) {
            d[0]=8;

            openclock.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            openclock.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            openclock.putExtra(AlarmClock.EXTRA_HOUR, d[0]);
            openclock.putExtra(AlarmClock.EXTRA_MINUTES, 0);
            openclock.putExtra(AlarmClock.EXTRA_MESSAGE, bdemo);
            openclock.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);

        }
        if(lflag==1) {

            d[1]=13;

            openclock1.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            openclock1.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            openclock1.putExtra(AlarmClock.EXTRA_HOUR, d[1]);
            openclock1.putExtra(AlarmClock.EXTRA_MINUTES, 0);
            openclock1.putExtra(AlarmClock.EXTRA_MESSAGE, ldemo);
            openclock1.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);

        }
        if(dflag==1) {
            d[2]=19;

            openclock2.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            openclock2.putExtra(AlarmClock.EXTRA_HOUR, d[2]);
            openclock2.putExtra(AlarmClock.EXTRA_MINUTES, 0);
            openclock2.putExtra(AlarmClock.EXTRA_MESSAGE, ddemo);
            openclock2.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);

        }

    }

    public void bgo(View v)
    {
        startActivity(openclock);
    }
    public void lgo(View v)
    {
        startActivity(openclock1);
    }
    public void dgo(View v)
    {
        startActivity(openclock2);
    }


}
