package com.example.mahsunaltin.lambdahealth.Symptoms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahsunaltin.lambdahealth.R;

//Hastalik Belirtileri ekrani icin kullanilan recyclerview elemaninin adaptor sinifi
public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private int         mNumberItems;
    private String[]    items;
    private final       ListItemClickListener mOnClickListener;


    public ItemAdapter(int numberOfItems, String[] itemList, ListItemClickListener listener){
        mNumberItems        = numberOfItems;
        items               = itemList;
        mOnClickListener    = listener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context         = parent.getContext();
        int layoutIdForListItem = R.layout.item_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view               = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ItemHolder viewHolder   = new ItemHolder(view, mOnClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        String[] list = items[position].split("---");
        holder.bind(list[0]);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public void setData(String[] nameData) {
        items           = nameData;
        mNumberItems    = nameData.length;
        notifyDataSetChanged();
    }

    //Tiklaninlan elemanin degerini dondurur
    public String returnData (int clickedItem){
        String[] list   = items[clickedItem].split("---");
        return list[1];
    }

    //Tiklaninlan elemanin ismini dondurur
    public String returnIsim (int clickedItem){
        String[] list   = items[clickedItem].split("---");
        return list[0];
    }
}
