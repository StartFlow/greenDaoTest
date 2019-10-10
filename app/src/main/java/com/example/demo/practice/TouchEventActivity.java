package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.demo.practice.widget.TouchTestView;

public class TouchEventActivity extends AppCompatActivity {

    private final String TAG = "TouchEvent";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_layout);
        setUpView();
    }

    private void setUpView(){
        ConstraintLayout parent = findViewById(R.id.parent_layout);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"parent");
            }
        });

        TouchTestView child = findViewById(R.id.child_view);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"child");
            }
        });
    }
}
