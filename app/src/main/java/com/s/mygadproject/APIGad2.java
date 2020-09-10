package com.s.mygadproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.s.mygadproject.save.Constant.BASE_URL;


public class APIGad2 {
    private static Retrofit retrofit =null;
    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
