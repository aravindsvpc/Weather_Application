package com.example.weather_app.Retrofit;

import com.google.gson.annotations.SerializedName;

public class wind {
    @SerializedName("speed")
    String speed;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
