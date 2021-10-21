package com.example.marketapp.service;

import static com.example.marketapp.service.RequestBase.BASE_URL;

import com.example.marketapp.models.GetStoreF;
import com.example.marketapp.models.LoginResponse;
import com.example.marketapp.models.ResponseRegister;
import com.example.marketapp.models.UserRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CallApi {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    CallApi callApi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CallApi.class);


    @POST("api/register")
    Call<ResponseRegister> postRegister(@Body UserRegister user);

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> postLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/activeEmail")
    Call<ResponseRegister> postActiveEmail(
            @Field("email") String email,
            @Field("otp") int otp
    );

    @GET("api/getStore")
    Call<GetStoreF> getAllStore(@Query("token") String token);

    @POST("serverda2/update.php")
    Call<com.example.marketapp.service.ServerResponse> updateInterface(@Body com.example.marketapp.service.ServerRequest request);
}
