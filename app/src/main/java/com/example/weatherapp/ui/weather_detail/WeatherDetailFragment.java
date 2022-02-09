package com.example.weatherapp.ui.weather_detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.Prefs;
import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.databinding.FragmentWeatherBinding;
import com.example.weatherapp.databinding.FragmentWeatherDetailBinding;

public class WeatherDetailFragment extends BaseFragment <FragmentWeatherDetailBinding> {


    @Override
    protected FragmentWeatherDetailBinding bind() {
        return FragmentWeatherDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setupListeners() {

        binding.btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs prefs = new Prefs(requireContext());
                prefs.saveCity(binding.etCity.getText().toString());
                navController.navigateUp();
                navController.navigate(R.id.weatherFragment);
            }
        });


    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {

    }
}