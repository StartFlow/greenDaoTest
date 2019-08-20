package com.example.demo.greendaotest.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.demo.greendaotest.application.MyApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PicViewRepository {
    private static final String BASE_URL = "http://guolin.tech/";
    private static ExecutorService netExecu = Executors.newSingleThreadExecutor();
    private OkHttpClient.Builder client = new OkHttpClient.Builder();
    private Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(ScalarsConverterFactory.create());

    private Retrofit retrofit = retrofitBuilder.build();
    private PicApi picApi = retrofit.create(PicApi.class);



    public LiveData<String> getPicUrl() {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        netExecu.execute(new Runnable() {
            @Override
            public void run() {
                picApi.getBingPic().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String url = response.body();
                        liveData.postValue(url);
                        cacheUrl(url);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.print(t.getMessage());
                    }
                });
            }
        });
        return liveData;
    }

    public String getCacheUrl(){
        String url = PreferenceManager.getDefaultSharedPreferences(MyApplication.application).getString("bing_pic",null);
        return url;
    }

    public void cacheUrl(String url){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.application).edit();
        editor.putString("bing_pic",url);
        editor.apply();
    }

}
