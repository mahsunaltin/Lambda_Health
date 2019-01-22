package com.example.mahsunaltin.lambdahealth.Users;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahsunaltin.lambdahealth.MainActivity;
import com.example.mahsunaltin.lambdahealth.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Kullanici kayid yapilmasini saglayan yer. Kullanicidan bilgilerini isteyip
//veritabanina kaydetme islemi yapiyor.
public class SignUpActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText ad       = findViewById(R.id.ad);
        final EditText soyAd    = findViewById(R.id.soyad);
        final EditText tc       = findViewById(R.id.tc);
        final EditText sehir    = findViewById(R.id.sehir);
        final EditText ilce     = findViewById(R.id.ilce);
        final EditText cinsiyet = findViewById(R.id.cinsiyet);
        final EditText tarih    = findViewById(R.id.tarih);
        final EditText sifre    = findViewById(R.id.sifre);

        final CheckBox checkBox       = findViewById(R.id.checkBox);

        Button button = findViewById(R.id.button_accept);

        //Dugmeye basildiginda verileri veritabanina yazan kod
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked() || ad.getText().toString().isEmpty() || soyAd.getText().toString().isEmpty() || tc.getText().toString().isEmpty() || sehir.getText().toString().isEmpty() || ilce.getText().toString().isEmpty() || cinsiyet.getText().toString().isEmpty() || tarih.getText().toString().isEmpty() || sifre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_LONG).show();
                }else{

                database    = FirebaseDatabase.getInstance();
                myRef       = database.getReference();

                myRef.child("Hastalar").child(tc.getText().toString()).child("isim").setValue(ad.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("soyisim").setValue(soyAd.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("tc_kimlik").setValue(tc.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("sehir").setValue(sehir.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("ilce").setValue(ilce.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("cinsiyet").setValue(cinsiyet.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("yas").setValue(tarih.getText().toString());
                myRef.child("Hastalar").child(tc.getText().toString()).child("sifre").setValue(sifre.getText().toString());

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);

                intent.putExtra(Intent.EXTRA_TEXT, tc.getText().toString() + "---" + sifre.getText().toString() );
                startActivity(intent);

                }
            }
        });

    }
}
