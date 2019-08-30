package com.example.demo.practice.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo.practice.R;
import com.example.demo.practice.entity.DouBanMovieItem;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieItemFragment extends DialogFragment {

    static MovieItemFragment item;
    private DouBanMovieItem douBanMoveItem;
    private Unbinder unbinder;
    @BindView(R.id.movie_pic)
    ImageView moviePic;

    @BindView(R.id.movie_name)
    TextView movieName;

    @BindView(R.id.rating)
    RatingBar bar;

    @BindView(R.id.durations)
    TextView durations;

    @BindView(R.id.director)
    TextView director;

    @BindView(R.id.casts)
    TextView casts;

    public static MovieItemFragment getInstance(String url){
        if (item == null){
            item = new MovieItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url",url);
            item.setArguments(bundle);
        }
        return item;
    }

    public MovieItemFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String imageUrl = douBanMoveItem.getSubjects().get(0).getImages().getMedium();
        String name = douBanMoveItem.getSubjects().get(0).getTitle();
        Glide.with(this).load(imageUrl).into(moviePic);
        durations.setText(douBanMoveItem.getSubjects().get(0).getDurations().toString());
        movieName.setText(name);
        bar.setRating((float) (douBanMoveItem.getSubjects().get(0).getRating().getAverage()));
        DouBanMovieItem.SubjectsBean.DirectorsBean directorsBean = douBanMoveItem.getSubjects().get(0).getDirectors().get(0);
        director.setText(directorsBean.getName()+" "+ directorsBean.getName_en());
        List<DouBanMovieItem.SubjectsBean.CastsBean> list = douBanMoveItem.getSubjects().get(0).getCasts();
        StringBuilder sb = new StringBuilder();
        for (DouBanMovieItem.SubjectsBean.CastsBean castsBean:list){
            sb.append(castsBean.getName());
            sb.append("/");
        }
        sb.replace(sb.lastIndexOf("/"),sb.lastIndexOf("/"),"");
        casts.setText(sb.toString());
        bar.setMax(10);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle!=null){
            String jsonStr = getArguments().getString("url");
            douBanMoveItem = new Gson().fromJson(jsonStr,DouBanMovieItem.class);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_layout,container,false);
        unbinder = ButterKnife.bind(this,view);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return  view;
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

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
