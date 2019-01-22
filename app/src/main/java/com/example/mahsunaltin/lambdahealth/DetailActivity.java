package com.example.mahsunaltin.lambdahealth;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;



import com.example.mahsunaltin.lambdahealth.Symptoms.SymptomsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Uygulamanin kayid ekranindan sonra gelen ve uygulamada ne yapmak istedigimizi secmemize olanak
//sunan ekran.

public class DetailActivity extends AppCompatActivity {
    DatabaseReference   myRef;
    DatabaseReference   database;
    TextView            textView;
    DatabaseReference   myRef_Value;
    String              sehirIsmi;
    String              tc_kimlik_no;
    TextView            maxMinValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        CardView hastalıkNe = findViewById(R.id.HastalıkNe);

        hastalıkNe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, SymptomsActivity.class);
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.welcomeString);

        tc_kimlik_no = "";

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            tc_kimlik_no = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
        }

        //Bu sayede kullanicinin adini ekrana bastirir
        database = FirebaseDatabase.getInstance().getReference();

        myRef = database.child("Hastalar/" + tc_kimlik_no + "/isim");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                textView.setText("Hoşgeldiniz " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef_Value = database.child("Hastalar/" + tc_kimlik_no + "/sehir");

        myRef_Value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sehirIsmi = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ImageView imageView = findViewById(R.id.iconForWeather);

        imageView.setImageDrawable(getDrawable(R.drawable.bulutlu));

    }
}


