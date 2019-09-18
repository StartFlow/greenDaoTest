package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.demo.practice.adapter.DiffUtilTestAdapter;
import com.example.demo.practice.decoration.GalleryItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewGroupActivity extends AppCompatActivity {


//    @BindView(R.id.parent)
//    CustomViewGroup parent;
    private ItemTouchHelper.Callback itemCallBack;

    @BindView(R.id.zoro)
    AppCompatImageView view1;

    @BindView(R.id.sanji)
    AppCompatImageView view2;

    @BindView(R.id.luffy)
    AppCompatImageView view3;

    @BindView(R.id.layout_manage_rv)
    RecyclerView recyclerView;

    private int offsetX = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewgroup_layout);
        ButterKnife.bind(this);
        View.OnClickListener listener = getListener();
        view1.setOnClickListener(listener);
        view2.setOnClickListener(listener);
        view3.setOnClickListener(listener);

        List<String> list = new ArrayList<>();
        list.add("红");
        list.add("橙");
        list.add("黄");
        list.add("绿");
        list.add("青");
        list.add("蓝");
        list.add("紫");
        Collections.reverse(list);
        DiffUtilTestAdapter adapter = new DiffUtilTestAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        LinearSnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GalleryItemDecoration());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                offsetX+=dx;
                int position = offsetX/GalleryItemDecoration.mCosumerX;
                float percent =(float) (offsetX-position*GalleryItemDecoration.mCosumerX)/GalleryItemDecoration.mCosumerX;
                setAnima(recyclerView,percent,position);

            }
        });



//        itemCallBack = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP|ItemTouchHelper.DOWN) {
//
//            @Override
//            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                double swip  = Math.sqrt(dX*dX+dY*dY);
//                double fraction = swip/(recyclerView.getWidth()*getSwipeThreshold(viewHolder));
//                if (fraction>1){
//                    fraction=1;
//                }
//                int childCount = recyclerView.getChildCount();
//                for (int a =0;a<childCount;a++){
//                    View child = recyclerView.getChildAt(a);
//                    int level = childCount-a-1;
//                    if (level>0){
//                        child.setScaleX((float)(1-0.05f+fraction*0.05f));
//                        if (level<4-1){
//                            child.setScaleY((float)(1-0.05f*level+fraction*0.05f));
//                            child.setTranslationY((float)(25*level-fraction*25));
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//                            String  team = list.remove(viewHolder.getLayoutPosition());
//                            list.add(0,team);
//                            adapter.notifyDataSetChanged();
//            }
//        };
//        ItemTouchHelper helper = new ItemTouchHelper(itemCallBack);
//        helper.attachToRecyclerView(recyclerView);
    }

    private void setAnima(RecyclerView recyclerView,float percent,int position){
        float minscale = 0.8f;
        View leftView =  recyclerView.getLayoutManager().findViewByPosition(position-1);
        View centerView = recyclerView.getLayoutManager().findViewByPosition(position);
        View rightView = recyclerView.getLayoutManager().findViewByPosition(position + 1);


            if (leftView != null) {
                leftView.setScaleX(minscale + (1 - minscale) * percent);
                leftView.setScaleY(minscale + (1 - minscale) * percent);
            }

            if (centerView != null) {
                centerView.setScaleX(1 - (1 - minscale) * percent);
                centerView.setScaleY(1 - (1 - minscale) * percent);
            }

            if (rightView != null) {
                rightView.setScaleX(minscale + (1 - minscale) * percent);
                rightView.setScaleY(minscale + (1 - minscale) * percent);
            }

        Log.e("Scroll","percent = " + percent + "position  = + " +position);

    }






    private View.OnClickListener getListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomViewGroupActivity.this, (String)v.getTag(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
