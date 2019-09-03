package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.example.demo.practice.adapter.DiffUtilTestAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiffUtilActivity extends AppCompatActivity {

    public RecyclerView rv;
    private DiffUtilTestAdapter adapter;
    private SparseArray<List<String>> map;
    final List<String> list1 = Arrays.asList("Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal");
    final List<String> list2 = Arrays.asList("Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel",  "Balaton", "Bandal");
    final List<String> list3 = Arrays.asList( "Bakers", "Baladi", "Balaton", "Bandal");
    final List<String> list4 = Arrays.asList( "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi");
    final List<String> list5 = Arrays.asList( "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffutil_layout);
        ButterKnife.bind(this);
        rv = findViewById(R.id.data_rv);
        map = new SparseArray<>();
        map.put(0,list1);
        map.put(1,list2);
        map.put(2,list3);
        map.put(3,list4);
        map.put(4,list5);
        adapter = new DiffUtilTestAdapter(list3);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }



    @OnClick(R.id.list_data_change)
    public void change(){
        int index =  new Random().nextInt(4);
        List<String> newList = map.get(index);
        adapter.setList(newList);
    }
}
