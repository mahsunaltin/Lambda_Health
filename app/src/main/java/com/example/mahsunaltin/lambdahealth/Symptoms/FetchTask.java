package com.example.mahsunaltin.lambdahealth.Symptoms;

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

//Hastalik Belirtileri ekrani icin kullanilan verilerin kullanilabilir hale
//getirildigi yer.
public class FetchTask extends AsyncTask<String, Void, String[]> {

    ItemAdapter itemAdapter;

    public FetchTask(ItemAdapter mAdaper) {
        itemAdapter = mAdaper;
    }

    @Override
    protected String[] doInBackground(String... urlStrings) {
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
            return getDataFromJson(data);
        } catch (JSONException e) {
            Log.e("FetchWeatherTask", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String[] string) {
        super.onPostExecute(string);

        itemAdapter.setData(string);
    }

    //Hastalik belirtilerinin isminin ve ID sinin cekildigi kod.
    private String[] getDataFromJson(String JsonStr) throws JSONException {

        JSONArray jsonArray = new JSONArray(JsonStr);
        String[] list       = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject symptoms = jsonArray.getJSONObject(i);
            String symptomName  = symptoms.getString("Name");
            String symptomID    = symptoms.getString("ID");
            list[i]             = symptomName + "---" + symptomID;
        }

        return list;
    }



}
