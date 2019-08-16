package com.example.demo.greendaotest.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.greendaotest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PopMenu extends DialogFragment {

    @BindView(R.id.ice)
    TextView ice;

    @BindView(R.id.hotdog)
            TextView hotdog;

    @BindView(R.id.berry)
            TextView berry;

    @BindView(R.id.happy)
            TextView happy;

    @BindView(R.id.pisa)
            TextView pisa;
    @BindView(R.id.egg)
            TextView egg;

    Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        if (window!=null){
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        View view = inflater.inflate(R.layout.popmenu_layout,null);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Drawable ice = getResources().getDrawable(R.drawable.ic_ice);
        ice.setBounds(0,0,96,96);
        this.ice.setCompoundDrawables(null,ice,null,null);
        Drawable hotdog = getResources().getDrawable(R.drawable.ic_hotdog);
        hotdog.setBounds(0,0,96,96);
        this.hotdog.setCompoundDrawables(null,hotdog,null,null);
        Drawable egg = getResources().getDrawable(R.drawable.ic_egg);
        egg.setBounds(0,0,96,96);
        this.egg.setCompoundDrawables(null,egg,null,null);
        Drawable happy = getResources().getDrawable(R.drawable.ic_happy);
        happy.setBounds(0,0,96,96);
        this.happy.setCompoundDrawables(null,happy,null,null);
        Drawable pisa = getResources().getDrawable(R.drawable.ic_pisa);
        pisa.setBounds(0,0,96,96);
        this.pisa.setCompoundDrawables(null,pisa,null,null);
        Drawable berry = getResources().getDrawable(R.drawable.ic_berry);
        berry.setBounds(0,0,96,96);
        this.berry.setCompoundDrawables(null,berry,null,null);

    }

    @OnClick({R.id.ice,R.id.hotdog,R.id.berry,R.id.happy,R.id.pisa,R.id.egg})
    public void onClick(View view){
        Toast.makeText(getContext(), ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            Window window = getDialog().getWindow();
            //设置对话框背景
            getDialog().getWindow().getAttributes().dimAmount = (float) 0.85;
            if (window!=null){
                window.setLayout((int) (dm.widthPixels * 0.6), (int)(dm.heightPixels*0.6));
            }
        }
    }

    @OnClick(R.id.pop_bg)
    public void thisDismiss(){
        this.dismiss();
    }

}
