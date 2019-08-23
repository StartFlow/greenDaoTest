package com.example.demo.practice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demo.practice.adapter.PagingAdapter;
import com.example.demo.practice.model.PagingItemViewModel;
import com.example.demo.practice.util.InjectorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagingActivity extends AppCompatActivity {
    private PagingItemViewModel pagingItemViewModel;

    @BindView(R.id.rv_view)
    RecyclerView showRv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_layout);
        ButterKnife.bind(this);
        pagingItemViewModel = ViewModelProviders.of(this, InjectorUtil.getPagingItemFatory()).get(PagingItemViewModel.class);
        PagingAdapter adapter = new PagingAdapter();
        showRv.setLayoutManager(new LinearLayoutManager(this));
        showRv.setAdapter(adapter);
        pagingItemViewModel.getItemList().observe(this, new Observer<PagedList<String>>() {
            @Override
            public void onChanged(@Nullable PagedList<String> strings) {
                adapter.submitList(strings);
            }
        });
    }
}
