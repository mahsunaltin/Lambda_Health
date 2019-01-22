package com.example.mahsunaltin.lambdahealth.Diagnoses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mahsunaltin.lambdahealth.Advices.NasihatActivity;
import com.example.mahsunaltin.lambdahealth.R;

import java.util.ArrayList;

//Hastaligin tanisinin koyuldugu ekran.

public class DiognasisActivity extends AppCompatActivity {

    ArrayList<String> list;
    String[] s = {"a---a---a"};
    int a = 1;

    private DiagnoseAdapter mAdapter;
    private RecyclerView mNumbersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diognasis);


        //Onceki ekrandan gelen verileri almak icin kullanilmistir
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            list = intentThatStartedThisActivity.getStringArrayListExtra(Intent.EXTRA_TEXT);
        }

        //Recyclerview islemleri
        mNumbersList = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mAdapter = new DiagnoseAdapter(a, s);
        mNumbersList.setAdapter(mAdapter);

        //Api islemleri
        DiagnoseData data = new DiagnoseData(mAdapter);
        data.execute("https://sandbox-healthservice.priaid.ch/diagnosis?symptoms=" +
                list.toString().replaceAll(" ", "") +
                "&gender=female&year_of_birth=1999&" +
                "token=" +
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InlpbG1hei5zYW5saUBhZ3UuZWR1LnRyIiwicm9sZSI6IlVzZXIiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9zaWQiOiI0NTA0IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy92ZXJzaW9uIjoiMjAwIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IlByZW1pdW0iLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL2xhbmd1YWdlIjoiZW4tZ2IiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL2V4cGlyYXRpb24iOiIyMDk5LTEyLTMxIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9tZW1iZXJzaGlwc3RhcnQiOiIyMDE5LTAxLTE5IiwiaXNzIjoiaHR0cHM6Ly9zYW5kYm94LWF1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE1NDgwNjAwNjQsIm5iZiI6MTU0ODA1Mjg2NH0.-YqnMImOVX09kHFORFL-1iArO9l-_AmGq_Mm7wwEKwQ"+
                "&format=json&language=tr-tr");



        Button button_tavsiyeAl = findViewById(R.id.button_tavsiye);


        //Tiklandiginda tavsiye ekranina gecer ve onceki ekranda bulunan tani id si sayesinde
        //oneriler bulur.
        button_tavsiyeAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiognasisActivity.this, NasihatActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT,s);
                startActivity(intent);
            }
        });

    }
}
