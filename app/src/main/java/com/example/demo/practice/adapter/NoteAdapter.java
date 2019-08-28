package com.example.demo.practice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo.practice.R;
import com.example.demo.practice.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends BaseAdapter {

    public void setDataList(List<Note> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public NoteAdapter() {
        this.dataList = new ArrayList<>();
    }

    private List<Note> dataList;

    public static class ViewHolder {
        TextView content;
        TextView commit;
        public ViewHolder(View view) {
            content = view.findViewById(R.id.content_note);
            commit = view.findViewById(R.id.commit_note);
        }
    }

    public void remove(Note note){
        dataList.remove(note);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return dataList==null?0:dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder =(ViewHolder) convertView.getTag();
        }
        Note note = (Note) getItem(position);
        holder.content.setText(note.getContent());
        holder.commit.setText(note.getCommitTime());
        return convertView;
    }
}
