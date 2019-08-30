package com.example.demo.practice.workmanage;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyWork  extends Worker {
    final String BASE_URL = "http://guolin.tech/api/bing_pic";
    final String inTheater = "in_theaters";
    final String apiKey ="0b2bdeda43b5688921839c8ecb20399b";
    final String city = "广州";

    private final String Url = String.format("https://api.douban.com/v2/movie/%s?apikey=%s&city=%s&start=0&count=1",inTheater,apiKey,city);


    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //使用Android原生HTTP获取每日一图接口返回
        String results = "";
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            URL uri = new URL(Url);
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

        }catch (Exception e){
            e.printStackTrace();
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
        }
        Data data = new Data.Builder().putString("jsonStr",results).build();
        return Result.success(data);
    }
}