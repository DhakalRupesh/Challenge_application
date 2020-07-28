package com.rupesh.baji.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String BASE_URL = "http://10.0.2.2:30115/";
//    public static final String BASE_URL = "http://192.168.137.162:30115/";

    public static String token = "Bearer ";

    public static String imagePath = BASE_URL + "uploads/" ;

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
