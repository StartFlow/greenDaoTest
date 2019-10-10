package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.demo.practice.adapter.CommonAdapter;
import com.example.demo.practice.widget.BlurImageView;
import com.example.demo.practice.widget.SlidingShowImage;

public class RecyclerViewTestActivity extends AppCompatActivity {
    LinearLayoutManager mLinearLayoutManger;
    private int totalDy = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_test_layout);
        BlurImageView imageView = findViewById(R.id.bg_image);
        RecyclerView recyclerView = findViewById(R.id.rv_test);
        mLinearLayoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManger);
        recyclerView.setAdapter(new CommonAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy+=dy;
                totalDy %= mLinearLayoutManger.getHeight();
                float fraction = (float) totalDy/mLinearLayoutManger.getHeight();
                imageView.setBlurFraction(fraction);
                Log.e("fraction","fraction = " + fraction);

                int firstPos = mLinearLayoutManger.findFirstCompletelyVisibleItemPosition();
                int lastPos = mLinearLayoutManger.findLastCompletelyVisibleItemPosition();
                for (int a = firstPos; a <= lastPos; a++) {
                    View view = mLinearLayoutManger.findViewByPosition(a);
                    if (view == null || view.getTop() < 0)
                        continue;

                    SlidingShowImage showImage = view.findViewById(R.id.image_item);
                    if (showImage != null) {
                        float scrollDistance = mLinearLayoutManger.getHeight()-view.getHeight();
                        float top = view.getTop();
                        float percent = top/scrollDistance;
                        Log.e("SlidingShowImage","top = " +top + "scroll = " + scrollDistance );
                        showImage.setPercent(percent);
                       // 18026482238
                    }
                }
            }
        });
    }
}
