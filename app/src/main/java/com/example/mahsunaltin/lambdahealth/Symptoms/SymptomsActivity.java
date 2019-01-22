package com.example.mahsunaltin.lambdahealth.Symptoms;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mahsunaltin.lambdahealth.Diagnoses.DiognasisActivity;
import com.example.mahsunaltin.lambdahealth.R;

import java.util.ArrayList;

//Uygulamanin hastalik belirtilerini alip isledigi ekrandir. Hastalik tespiti yapilan sinifa belirtileri veri olarak
//gecirir.

public class SymptomsActivity extends AppCompatActivity implements ListItemClickListener {

    RecyclerView recyclerView;
    ItemAdapter mAdapter;

    String[] list = {""};
    int number      = 0;

    String token    = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InlpbG1hei5zYW5saUBhZ3UuZWR1LnRyIiwicm9sZSI6IlVzZXIiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9zaWQiOiI0NTA0IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy92ZXJzaW9uIjoiMjAwIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IlByZW1pdW0iLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL2xhbmd1YWdlIjoiZW4tZ2IiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL2V4cGlyYXRpb24iOiIyMDk5LTEyLTMxIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9tZW1iZXJzaGlwc3RhcnQiOiIyMDE5LTAxLTE5IiwiaXNzIjoiaHR0cHM6Ly9zYW5kYm94LWF1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE1NDgwNjAwNDIsIm5iZiI6MTU0ODA1Mjg0Mn0.pWM_PbheH0gIUTYlrmQO1RnFB8QdSxkYlrY104m8jDo";
    public static ArrayList<String> listOfSymptoms = new ArrayList<>();

    public static ArrayList<String> listOfSymptomsName = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        //Recyclerview tanimlama
        recyclerView = findViewById(R.id.symptomsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new ItemAdapter(number, list, this);
        recyclerView.setAdapter(mAdapter);

        //Api islemleri
        String URLString = "https://sandbox-healthservice.priaid.ch/symptoms?" +
                "token=" +
                token +
                "&format=json" +
                "&language=tr-tr";

        FetchTask dataTask = new FetchTask(mAdapter);
        dataTask.execute(URLString);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SymptomsActivity.this, DiognasisActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, listOfSymptoms);
                startActivity(intent);
            }
        });
    }

    //Secilen belirtiyi gostermek icin ekrana Toast ciktisi verme
    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (!listOfSymptomsName.contains(mAdapter.returnIsim(clickedItemIndex))){
            listOfSymptomsName.add(mAdapter.returnIsim(clickedItemIndex));
            listOfSymptoms.add(mAdapter.returnData(clickedItemIndex));
            Toast.makeText(getApplicationContext(), "Seçilen belirtiler: " + listOfSymptomsName.toString(), Toast.LENGTH_LONG).show();
        }else{

            listOfSymptomsName.remove(mAdapter.returnIsim(clickedItemIndex));
            listOfSymptoms.remove(mAdapter.returnData(clickedItemIndex));

            Toast.makeText(getApplicationContext(), "Seçilen belirtiler: " + listOfSymptomsName.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
