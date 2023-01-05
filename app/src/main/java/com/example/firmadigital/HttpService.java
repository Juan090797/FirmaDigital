package com.example.firmadigital;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpService {
    private static final String TAG =  HttpService.class.getName();


    private String urlService;

    public HttpService(String urlService) {
        this.urlService = urlService;
    }

    public String get(String param, String query) {
        StringBuilder result = new StringBuilder();
        try {
            String finalPath = urlService + (param==null?"":"/"+param)+(query==null?"":"?"+query);
            Log.i(TAG,finalPath);
            URL url = new URL(finalPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String linea;
            while ((linea = rd.readLine()) != null) {
                result.append(linea+"\n");
            }
            rd.close();

        } catch (Exception e) {
            Log.e(TAG,  urlService+  e.toString());
        }
        Log.i(TAG,result.toString());
        return result.toString();
    }

    public String post(String param, String query, String body) {
        StringBuilder result = new StringBuilder();
        try {
            String finalPath = urlService + (param==null?"":"/"+param)+(query==null?"":"?"+query);
            URL url = new URL(finalPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String linea;
            while ((linea = rd.readLine()) != null) {
                result.append(linea);
            }
            rd.close();

        } catch (Exception e) {
            Log.e(TAG ,e.toString());
        }
        Log.i(TAG,result.toString());
        return result.toString();
    }

}
