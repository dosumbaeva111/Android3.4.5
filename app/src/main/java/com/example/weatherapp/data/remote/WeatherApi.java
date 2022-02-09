package com.example.weatherapp.data.remote;


import com.example.weatherapp.data.models.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Call<MainResponse> getWeather (
            @Query("q") String city,
            @Query("appid") String appid,
            @Query("units") String units
    );
}
