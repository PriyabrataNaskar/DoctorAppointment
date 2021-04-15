package com.blogspot.priyabratanaskar.firebaselogin;

import com.blogspot.priyabratanaskar.firebaselogin.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Priyabrata Naskar on 15-04-2021.
 */
public interface JSONPlaceHolderAPI {

//    //String s= "?country=in&category=health&apiKey=84a9ce0f3b9040bf8edf144d10a95708";
//    @GET("top-headlines")
//    Call<ResponseModel> getNews(@Query("country")String country, @Query("category")String category, @Query("apiKey") String apiKey);
    @GET("top-headlines?country=in&category=health&apiKey=84a9ce0f3b9040bf8edf144d10a95708")
    Call<ResponseModel> getResponse();
}