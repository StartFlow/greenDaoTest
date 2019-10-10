package com.example.demo.practice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.practice.R;
import com.example.demo.practice.widget.SlidingShowImage;

public class CommonAdapter extends RecyclerView.Adapter{

    private final String TAG = "CommonAdapter";

    private static int TYPE_STRING = 101;
    private static int TYPE_IMAGE = 102;
    private int holderCount = 0;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        holderCount++;
        Log.e(TAG,"hold = " + holderCount);
        if (i==TYPE_STRING){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rc_string_item,viewGroup,false);
            return new StringHolder(view);
        }else if (i==TYPE_IMAGE){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_image_item,viewGroup,false);
            return new ImageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }


    @Override
    public int getItemViewType(int position) {
        int type = position%3==0?TYPE_IMAGE:TYPE_STRING;
        return type;
    }

    @Override
    public int getItemCount()
    {
        return 30;
    }

    static class StringHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public StringHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.string_item);
        }
    }

    static class ImageHolder extends RecyclerView.ViewHolder{
        SlidingShowImage image;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_item);
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ImageHolder){
            ((ImageHolder) holder).image.setPercent(0);
        }
    }
}
