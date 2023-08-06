package com.example.weather_app.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("main")
    private Main main;
    @SerializedName("wind")
    private wind wind;

    public com.example.weather_app.Retrofit.wind getWind() {
        return wind;
    }

    public void setWind(com.example.weather_app.Retrofit.wind wind) {
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
