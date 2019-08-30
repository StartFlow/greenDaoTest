package com.example.demo.practice.model.repository;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PicApi {

    @GET("api/bing_pic")
    Call<String> getBingPic();

}
