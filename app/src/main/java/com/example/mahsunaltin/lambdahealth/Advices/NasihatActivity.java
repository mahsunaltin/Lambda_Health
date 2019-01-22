package com.example.mahsunaltin.lambdahealth.Advices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mahsunaltin.lambdahealth.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Olagan hastalik teshisi yapildiktan sonra veri tabanindan ilgili hastaliga gore
//kullanici tavsiyesi almaya yarayan kod.
//Vakit yetersizligi nedeniyle tamamlanamamistir

public class NasihatActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasihat);

        final EditText editText   = findViewById(R.id.editText);
        Button button       = findViewById(R.id.button_tavsiyeYolla);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String benimTavsiyem = editText.getText().toString();
                database    = FirebaseDatabase.getInstance();
                myRef       = database.getReference();

            }
        });

    }
}
