package com.rupesh.baji.api;

import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.Result;
import com.rupesh.baji.serverresponse.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Resulti {
    @POST("/result")
    Call<Void> submitResult(@Body Result result);

    @PUT("/result/{id}")
    Call<Void> updateChallengeStatus(@Path("id") String id, @Body Challenge upchallenge);

    @Multipart
    @POST("/uploads")
    Call<ImageResponse> uploadResultImage(@Part MultipartBody.Part img);

    @GET("/result")
    Call<List<Result>> getResultForVerification(@Header("Authorization") String token);

//    @GET("/result")
//    Call<List<Result>> getResultForVerification();

}
