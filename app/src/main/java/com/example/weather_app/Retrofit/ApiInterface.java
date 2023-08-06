package com.example.weather_app.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("weather?appid=544513c71589c99d10811c804acd5bc6&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
