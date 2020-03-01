package com.example.gmit_sdp.beinghuman;

/**
 * Created by DELL on 02-08-2017.
 */


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class ItemThreeFragment extends Fragment {
    Button logout;
    ImageView edit;
//    Firebase f;
    TextView name_display,email,plate;
    View h;
    String un, j_string;


    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        TextView about_us;
        String r;
        h = inflater.inflate(R.layout.fragment_item_three, container, false);
        //un ="yash12";//getActivity().getIntent().getExtras().getString("username");
        Bundle arguments = getArguments();
        un = arguments.getString("user");
        new BackgroundWork1().execute(un);

        Button logout= h.findViewById(R.id.button6);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),Login.class);
                startActivity(intent);

                getActivity().finish();

            }
        });
        Bundle b = new Bundle();
        return h;
    }
    public class BackgroundWork1 extends AsyncTask<String, Void, String> {

        String json_url;
        String json_string;

        @Override
        protected void onPreExecute() {
            json_url = "http://dhruvesh09.000webhostapp.com/Medicare/Profile.php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String name1=params[0];
                //final String value= getIntent().getExtras().getString("name");

                //Toast.makeText(HomePage2.this,bname,Toast.LENGTH_SHORT).show();
                //String url20 = "http://dhruvesh09.000webhostapp.com/UserView/setter.php";
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("name","UTF-8")+"="+ URLEncoder.encode(name1,"UTF-8");
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

        // btn=(Button)findViewById(R.id.book);
        JSONObject jsonObject = new JSONObject(s);
        JSONArray jsonArray = jsonObject.getJSONArray("response");
        final setterAdapter sadp = new setterAdapter(getActivity(), R.layout.row_layout);
        final ListView listView = (ListView) h.findViewById(R.id.lv1);
        listView.setAdapter(sadp);


        /*btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, DatetimeActivity.class);
                startActivity(i);
            }
        });*/
        int count = 0;
        String tname,tmobile,temail,tgender;

        while (count <= jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(count);
            tname = jo.getString("name");
            tmobile = jo.getString("mobile");
            temail = jo.getString("email");
            tgender = jo.getString("gender");
            setter st = new setter(tname,tmobile,temail,tgender);
            sadp.add(st);
            count++;
        }

    }


}

