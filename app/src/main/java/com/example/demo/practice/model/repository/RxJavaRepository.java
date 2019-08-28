package com.example.demo.practice.model.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaRepository {
    final String BASE_URL = "http://guolin.tech/api/bing_pic";


    public void getImageUrl(MutableLiveData<String> liveData){
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    getImageUrl(emitter);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                liveData.setValue(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("RxJava","NetWork get Data is complete");
            }
        };

        observable.subscribe(observer);
    }

    private void getImageUrl(ObservableEmitter<String> emitter){
        String results = "";
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            URL uri = new URL(BASE_URL);
            connection = (HttpURLConnection) uri.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line);
            }
            results = sb.toString();
            emitter.onNext(results);
        }catch (Exception e){
            e.printStackTrace();
            emitter.onError(e);
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                connection.disconnect();
            }
            emitter.onComplete();
        }
    }

}
