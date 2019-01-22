package com.example.mahsunaltin.lambdahealth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahsunaltin.lambdahealth.Users.SignUpActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Sisteme kayid olmak isteyen kullanicinin verilerinin tedkik edip
//kullanici verilerinin gecerli olup olmadigini kontrol eder.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ed1      = findViewById(R.id.ed1);
        final EditText ed2      = findViewById(R.id.ed2);

        final Button button_in  = findViewById(R.id.button_in);
        final Button button_up  = findViewById(R.id.button_up);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String forecastStr[] = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT).split("---");

            ed1.setText(forecastStr[0]);
            ed2.setText(forecastStr[1]);


        }
        //Veri formunun eksiksik tamamlanip tamamlanmadigini kontrol eder.
        button_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed1.getText().toString().isEmpty() || ed2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "TC kimlik numarınızı ve şifrenizi doldurup, tekrar deneyiniz.", Toast.LENGTH_LONG).show();
                }else {

                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = database.child("Hastalar/" + ed1.getText().toString() + "/sifre");

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            if (ed2.getText().toString().equals(value)) {
                                Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);
                                intentDetail.putExtra(Intent.EXTRA_TEXT, ed1.getText().toString());
                                startActivity(intentDetail);
                            }else{
                                Toast.makeText(getApplicationContext(), "Kullanıcı kayıtlı değil, lütfen tekrar deneyiniz.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });



        button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });



    }

}