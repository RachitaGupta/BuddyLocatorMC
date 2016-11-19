package com.example.rgrac.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgrac on 11/14/2016.
 */
public class FindFriends  extends AsyncTask<String,Void,String> {
    private String statusField;
    private Context context;
    public List<Friends> allNames = new ArrayList<Friends>();

    URL urlregister,urllogin;
    HttpURLConnection conn;
    public FindFriends(Context context, String statusField,ArrayList<Friends> friends) {
        this.context = context;
        this.statusField = statusField;
        this.allNames=friends;
        //System.out.println("Welcome");
        try {
            urlregister= new URL("http://192.168.0.12/buddylocator/findfriends.php");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {

            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) urlregister.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("username", arg0[0]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {
                int response_code = conn.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

    }

    @Override
    protected void onPostExecute(String result){
        if(result.equalsIgnoreCase("no locations found")) {
            this.statusField="no locations found";
            System.out.println("status "+statusField);
        }
        else if(result.equalsIgnoreCase("no friends found")) {
            this.statusField="no friends found";
            System.out.println("status "+statusField);
        }
        else {
            Friends friend = null;
            String splitComma[] = result.split("\\$\\$");

            String splitsurvey[]=splitComma[0].split("::");
            //inserting questions
            for(int i=1;i<splitComma.length;i++){
                String info[] = splitComma[i].split("::");
                friend.setUsername(info[0]);
                friend.setLatitude(Double.parseDouble(info[1]));
                friend.setLongitude(Double.parseDouble(info[2]));
                allNames.add(friend);
            }
//            Toast toast=Toast.makeText(context,"All friends", Toast.LENGTH_SHORT);
//            toast.show();

            return;
        }
        return;
    }
}

