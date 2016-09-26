package com.foodorderingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by User on 9/18/2016.
 */
public class GetJSON {
    private String jsonURL;
    //private String jsonString;
    private Context context;
    private Class<?> cls;
    private String post;
    //private URL url;
    //private HttpURLConnection con;

    //TextView textView;

    public GetJSON(Context context, String jsonURL, Class<?> cls, String post){
        this.jsonURL = jsonURL;
        this.context = context;
        this.cls = cls;
        this.post = post;
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

                    if(post != null){
                        return postData();
                    }else{
                        URL url = new URL(jsonURL);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        StringBuilder sb = new StringBuilder();

                        bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                        String json;
                        while ((json = bufferedReader.readLine()) != null) {
                            sb.append(json + "\n");
                        }
                        return sb.toString().trim();
                    }
                    //return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Intent intent = new Intent(context, cls);
                intent.putExtra("JSONString", s);
                if(post != null){
                    intent.putExtra("restaurantID",post);
                }
                context.startActivity(intent);
            }
        }
        RetrieveJSON retrieveJSONj = new RetrieveJSON();
        retrieveJSONj.execute();
    }

    public String postData() {
        StringBuilder sb = new StringBuilder();
            try {
                String data = URLEncoder.encode("restaurant", "UTF-8")
                        + "=" + URLEncoder.encode(post, "UTF-8");

                URL url = new URL(jsonURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);

                //Creating an output stream
                OutputStream os = con.getOutputStream();

                //Writing parameters to the request
                //We are using a method getPostDataString which is defined below
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                os.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        return sb.toString().trim();
    }


}
