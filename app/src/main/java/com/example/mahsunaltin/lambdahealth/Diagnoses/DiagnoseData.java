package com.example.mahsunaltin.lambdahealth.Diagnoses;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Tani konulan ekranin recyclerview elemaninin ihtiyaci oldugu veriiyi
//islemlere tabi tutup kullanabilir olmasini saglayan kod.

public class DiagnoseData extends AsyncTask<String, Void, String[]> {

    DiagnoseAdapter adapter;

    //Api dan hastalik tanilarini ceken kod.

    public DiagnoseData(DiagnoseAdapter adapter){
        this.adapter = adapter;
    }
    @Override
    protected String[] doInBackground(String... strings){
        HttpURLConnection urlConnection = null;
        BufferedReader reader           = null;
        String forecastJsonStr          = null;
        try {
            URL weatherURL = new URL(strings[0]);
            urlConnection = (HttpURLConnection) weatherURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() != 0) {
                    forecastJsonStr = buffer.toString();
                }
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error ", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
        }
        try {
            return getDataFromJson(forecastJsonStr);
        } catch (JSONException e) {
            Log.e("FetchWeatherTask", e.getMessage(), e);
        }

        return null;

    }

    @Override
    protected void onPostExecute(String[] s) {
        super.onPostExecute(s);

        for (int i = 0; i<s.length; i++)
            Log.v("datacik", s[i]);

        adapter.setData(s);
    }

    //Hastalk tanisinin ismini, olasiligini ve ID sini ceken kod.

    private String[] getDataFromJson(String JsonStr)
            throws JSONException {

        JSONArray array = new JSONArray(JsonStr);
        String[] resultStrs = new String[array.length()];

        for (int i=0; i<array.length(); i++){
            String name     = array.getJSONObject(i).getJSONObject("Issue").getString("Name");
            int accuracy    = array.getJSONObject(i).getJSONObject("Issue").getInt("Accuracy");
            String spec     = array.getJSONObject(i).getJSONArray("Specialisation").getJSONObject(0).getString("Name");
            int id          = array.getJSONObject(i).getJSONObject("Issue").getInt("ID");

            resultStrs[i] = name+"---"+accuracy+"---"+spec+"---"+id;
        }

        return  resultStrs;
    }



}
