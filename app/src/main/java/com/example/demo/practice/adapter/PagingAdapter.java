package com.example.demo.practice.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.practice.R;

public class PagingAdapter extends PagedListAdapter<String,PagingAdapter.MyViewHolder> {


    public PagingAdapter() {
        super(call);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_layout,viewGroup,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String content = getItem(i);
        myViewHolder.textView.setText(content);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_word);
        }
    }

    public static final DiffUtil.ItemCallback<String> call = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String s, @NonNull String t1) {
            return s.equals(t1);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String s, @NonNull String t1) {
            return s.equals(t1);
        }
    };


}
