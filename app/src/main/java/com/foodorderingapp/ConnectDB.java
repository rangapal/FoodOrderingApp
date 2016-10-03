package com.foodorderingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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
 * Created by User on 10/3/2016.
 */

public class ConnectDB extends AsyncTask<String, Void, String> {
    private String jsonURL;
    private String post;
    private Context context;
    private AsyncResponse asyncResponse;
    ProgressDialog loading;

    public ConnectDB(Context context, String jsonURL, AsyncResponse asyncResponse, String post){
        this.post = post;
        this.context = context;
        this.asyncResponse = asyncResponse;
        this.jsonURL = jsonURL;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context, "Please Wait...", null, true, true);
    }


    @Override
    protected String doInBackground(String... params) {
        return getJSONData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loading.dismiss();
        asyncResponse.onTaskComplete(s);
    }

    public String getJSONData() {
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
