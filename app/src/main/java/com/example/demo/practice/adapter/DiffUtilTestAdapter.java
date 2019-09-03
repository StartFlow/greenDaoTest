package com.example.demo.practice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.practice.DiffUtil.MyDiffUtil;
import com.example.demo.practice.R;

import java.util.ArrayList;
import java.util.List;

public class DiffUtilTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;

    public DiffUtilTestAdapter(List<String> list) {
        if (list==null){
            list = new ArrayList<>();
        }
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_layout,viewGroup,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder){
            ((MyViewHolder)viewHolder).item.setText(list.get(i));
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_word);
        }
    }


    public void setList(List<String> list) {
        //数据量较大时最好启用子线程计算
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyDiffUtil(list,this.list));
        this.list = list;
        result.dispatchUpdatesTo(this);
    }

}
