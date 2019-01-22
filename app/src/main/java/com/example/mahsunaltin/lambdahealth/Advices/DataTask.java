package com.example.mahsunaltin.lambdahealth.Advices;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Uygulamada kullanacak gunluk sicaklik verisini internetten alinmasini saglayan kod

public class DataTask extends AsyncTask<String, String, String> {

    String value;

    // Api dan verileri ceken kod

    @Override
    protected String doInBackground(String... urlStrings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader           = null;
        String data                     = null;
        try {
            URL weatherURL  = new URL(urlStrings[0]);
            urlConnection   = (HttpURLConnection) weatherURL.openConnection();
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
                    data = buffer.toString();
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
            value = getMaxTempForDay(data, 0 );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        Log.v("Merhaba", string);

    }

    //Gunluk en yuksek sicakligi almak icin kullanilan kod

    public static String getMaxTempForDay(String wJsonStr, int dayIndex)
            throws JSONException {
        JSONObject mainObject = new JSONObject(wJsonStr);
        JSONArray list = mainObject.getJSONArray("list");
        double max_tmp = list.getJSONObject(dayIndex)
                .getJSONObject("main")
                .getDouble("temp_max");
        return Double.toString(max_tmp);
    }

    public String returnValue (){
        return value;
    }
}