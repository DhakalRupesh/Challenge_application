package com.rupesh.baji.api;

import com.rupesh.baji.model.User;
import com.rupesh.baji.serverresponse.ImageResponse;
import com.rupesh.baji.serverresponse.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface Useri {
    @POST("/user/register")
    Call<Void> addUser(@Body User user);

    @FormUrlEncoded
    @POST("/user/login")
    Call<UserResponse> loginUser(@Field("uname") String username, @Field("pass") String password);

    @Multipart
    @POST("/uploads")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("user/retriveme")
    Call<User> getme(@Header("Authorization") String token);

    @PUT("user/updateme")
    Call<User> updateProfile(@Header("Authorization") String token, @Body User user);
}
