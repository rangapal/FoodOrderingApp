package com.foodorderingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 9/18/2016.
 */
public class GetJSON {
    private String jsonURL;
    //private String jsonString;
    private Context context;
    private Class<?> cls;
    //TextView textView;

    public GetJSON(Context context, String jsonURL, Class<?> cls){
        this.jsonURL = jsonURL;
        this.context = context;
        this.cls = cls;
        //textView = tx;
        getJSON();
    }

    private void getJSON(){
        class RetrieveJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait...", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(jsonURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //textView.setText(s);
                Intent intent = new Intent(context, cls);
                intent.putExtra("JSONString", s);
                context.startActivity(intent);
            }
        }
        RetrieveJSON retrieveJSONj = new RetrieveJSON();
        retrieveJSONj.execute();
    }
}
