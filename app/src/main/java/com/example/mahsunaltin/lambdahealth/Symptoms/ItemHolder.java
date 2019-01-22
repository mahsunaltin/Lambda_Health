package com.example.mahsunaltin.lambdahealth.Symptoms;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mahsunaltin.lambdahealth.R;

//Hastalik Belirtileri ekrani icin kullanilan recyclerview elemaninin verileri olusturan sinifi

public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView                        textView;
    private ListItemClickListener   mListener;

    public ItemHolder(@NonNull View itemView,  ListItemClickListener listener) {
        super(itemView);
        textView    = itemView.findViewById(R.id.belirtiIsmi);
        mListener   = listener;
        itemView.setOnClickListener(this);
    }

    void bind(String s){
        textView.setText(s);
    }

    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);
    }
}
