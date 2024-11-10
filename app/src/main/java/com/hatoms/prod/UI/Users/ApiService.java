package com.hatoms.prod.UI.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/users/register")
    Call<Integer> registerUser(@Body Register request);

    @POST("/users/login") // Укажите правильный путь для логина на сервере
    Call<Integer> login(@Body LoginRequest loginRequest);
}
