package com.example.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.models.MainResponse;
import com.example.weatherapp.data.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    public MainRepository repository;


    @Inject
    public WeatherViewModel(MainRepository repository){
        this.repository = repository;
    }
    public LiveData<Resource<MainResponse>> weatherLiveData;
    public void getWeather(String city){
        weatherLiveData = repository.getWeather(city);
    }
}
