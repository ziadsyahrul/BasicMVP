package com.ziadsyahrul.basicmvp.network;

import com.ziadsyahrul.basicmvp.model.LoginBody;
import com.ziadsyahrul.basicmvp.model.LoginResponse;
import com.ziadsyahrul.basicmvp.model.SingleUserResponse;
import com.ziadsyahrul.basicmvp.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // membuat endpoint login
    @POST("/api/login")
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    // membuat request Get data dari user
    @GET("/api/users")
    Call<UserResponse> getUser(
            @Query("per_page") int perPage);

    // Membuat endpoint untuk get data  single user
    @GET("api/users/{id}")
    Call<SingleUserResponse> getDataSingleUser(@Path("id") int id);

}
