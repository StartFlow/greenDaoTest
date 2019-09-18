package com.example.demo.practice.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.practice.R;
import com.example.demo.practice.diffutil.MyDiffUtil;
import com.example.demo.practice.widget.WaveBall;

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

        private WaveBall item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_word);
        }
    }


    public void setList(List<String> list) {
        //数据量较大时最好启用子线程计算
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyDiffUtil(list,this.list));

        result.dispatchUpdatesTo(new ListUpdateCallback() {
            @Override
            public void onInserted(int i, int i1) {

            }

            @Override
            public void onRemoved(int i, int i1) {

            }

            @Override
            public void onMoved(int i, int i1) {

            }

            @Override
            public void onChanged(int i, int i1, @Nullable Object o) {

            }
        });
        this.list = list;
        result.dispatchUpdatesTo(this);
    }

}
