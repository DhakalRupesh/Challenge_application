package com.rupesh.baji.api;

import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.serverresponse.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Challengei {

    @POST("/challenge")
    Call<Void> addChallenge(@Body Challenge challenge);

    @GET("/challenge")
    Call<List<Challenge>> getAllChallenges(@Header("Authorization") String token);

    @GET("/challenge/{id}")
    Call<Challenge> getOneChallenge(@Path("id") String id);

    @GET("/challenge/myAccepted")
    Call<List<Challenge>> getAcceptedChallenges(@Header("Authorization") String token);

    @PUT("/challenge/{id}")
    Call<Void> updateChallengeStatus(@Path("id") String id, @Body Challenge upchallenge);

    @Multipart
    @POST("/uploads")
    Call<ImageResponse> uploadChallengeImage(@Part MultipartBody.Part img);

}
