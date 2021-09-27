package com.example.marketapp.server;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("serverda2/login.php")
    Call<ServerResponse> loginInterface(@Body ServerRequest request);

    @POST("serverda2/register.php")
    Call<ServerResponse> registerInterface(@Body ServerRequest request);

    @POST("serverda2/update.php")
    Call<ServerResponse> updateInterface(@Body ServerRequest request);
}
