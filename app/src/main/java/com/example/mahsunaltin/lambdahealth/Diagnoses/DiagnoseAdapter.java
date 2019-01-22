package com.example.mahsunaltin.lambdahealth.Diagnoses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahsunaltin.lambdahealth.R;

//Tani konulan ekranin recyclerview elemaninin olusturulmasi icin kullanilan adaptor.

public class DiagnoseAdapter extends RecyclerView.Adapter<DiagnoseHolder> {

    private int         mNumberItems;
    private String[]    items;

    public DiagnoseAdapter(int numberOfItems, String[] itemList){
        mNumberItems    = numberOfItems;
        items           = itemList;
    }

    @NonNull
    @Override
    public DiagnoseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context         = parent.getContext();
        int layoutIdForListItem = R.layout.diagnose_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view               = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        DiagnoseHolder viewHolder = new DiagnoseHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnoseHolder holder, int position) {
        holder.bind(items[position]);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public void setData(String[] data) {
        items           = data;
        mNumberItems    = data.length;
        notifyDataSetChanged();
    }

    public int returnNumberOfItems(){
        return mNumberItems;
    }
}
