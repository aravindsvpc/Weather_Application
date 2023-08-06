package com.example.weather_app;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather_app.Retrofit.ApiClient;
import com.example.weather_app.Retrofit.ApiInterface;
import com.example.weather_app.Retrofit.Example;

import javax.xml.transform.ErrorListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView search,fav;
    TextView tempText,descText,humidityText,minText,maxText,windSpeedText;
    EditText textField;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.search);
        maxText = findViewById(R.id.maxTemp);
        minText = findViewById(R.id.minTemp);
        tempText = findViewById(R.id.tempText);
        descText = findViewById(R.id.descText);
        humidityText = findViewById(R.id.humidityText);
        textField = findViewById(R.id.textField);
        windSpeedText = findViewById(R.id.windSpeed);
        fav = findViewById(R.id.idFav);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This city is added to favourites", Toast.LENGTH_SHORT).show();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherData(textField.getText().toString().trim());
            }
        });

    }
        private void getWeatherData(String name){

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<Example> call = apiInterface.getWeatherData(name);

            call.enqueue(new Callback<Example>() {

                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    if(response.body()==null){
                        Toast.makeText(MainActivity.this, "Please enter valid city name", Toast.LENGTH_SHORT).show();

                    }else {

                        tempText.setText("Temp:" + " " + response.body().getMain().getTemp() + "°C");
                        descText.setText("Feels Like" + ": " + response.body().getMain().getFeels_like() + "°C");
                        humidityText.setText("Humidity" + ": " + response.body().getMain().getHumidity() + "%");
                        minText.setText("Min Temp:\n" + " " + response.body().getMain().getTemp_min() + "°C");
                        maxText.setText("Max Temp:\n" + " " + response.body().getMain().getTemp_max() + "°C");
                        windSpeedText.setText("Wind Speed: " + response.body().getWind().getSpeed() + "kph");
                        if (Double.parseDouble(response.body().getMain().getTemp()) < 20) {
                            //Toast.makeText(MainActivity.this, "less than 20", Toast.LENGTH_SHORT).show();
                            Dialog dialog = new Dialog(MainActivity.this);
                            TextView okay_text;
                            dialog.setContentView(R.layout.dialog);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(false);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                            okay_text = dialog.findViewById(R.id.okay_text);
                            okay_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Check all details", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.show();
                        }
                        if (Double.parseDouble(response.body().getMain().getTemp()) > 40) {
                            //Toast.makeText(MainActivity.this, "less than 20", Toast.LENGTH_SHORT).show();
                            Dialog dialog = new Dialog(MainActivity.this);
                            TextView okay_text;
                            dialog.setContentView(R.layout.dialog2);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(false);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                            okay_text = dialog.findViewById(R.id.okay_text2);
                            okay_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Check all details", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Please Check Internet Connection!!!", Toast.LENGTH_SHORT).show();
                }


            });

        }

}