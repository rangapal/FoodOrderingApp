package com.foodorderingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 10/4/2016.
 */

public class WriteToDatabase {
    ArrayList<String> columnName;
    ArrayList<String> dataTowrite;
    String url;
    ProgressDialog PD;
    RequestQueue requestQueue;
    Context context;

    public WriteToDatabase(ArrayList<String> columnName, ArrayList<String> dataTowrite, String url, Context context){
        this.dataTowrite = dataTowrite;
        this.columnName = columnName;
        this.url = url;
        this.context = context;
        PD = new ProgressDialog(context);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);
        requestQueue = Volley.newRequestQueue(context);
    }

    public void write(){
        PD.show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        Toast.makeText(context,
                                "User details saved successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
                Toast.makeText(context,
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                for(int i = 0; i< columnName.size(); i++){
                    //key is the variable name for php file on database
                    //value is the values from user input when login for first time
                    params.put(columnName.get(i), dataTowrite.get(i));
                }
                return params;
            }

        };
        requestQueue.add(postRequest);
    }



}
