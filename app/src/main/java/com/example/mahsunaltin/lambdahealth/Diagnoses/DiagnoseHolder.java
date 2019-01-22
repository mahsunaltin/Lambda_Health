package com.example.mahsunaltin.lambdahealth.Diagnoses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mahsunaltin.lambdahealth.R;

//Tani konulan ekranin recyclerview elemaninin olusturulmasi icin kullanilan kod.

public class DiagnoseHolder extends RecyclerView.ViewHolder {


    TextView textView1;
    TextView textView3;
    TextView chart;

    String s = "";

    public DiagnoseHolder(@NonNull View itemView) {
        super(itemView);

        textView1   = itemView.findViewById(R.id.tv1);
        chart       = itemView.findViewById(R.id.tv2);
        textView3   = itemView.findViewById(R.id.tv3);

    }

    void bind(String s){

        String[] list = s.split("---");

        textView1.setText(list[0]);
        textView3.setText(list[2]);

        chart.setText("%" + list[1]);


    }
}
