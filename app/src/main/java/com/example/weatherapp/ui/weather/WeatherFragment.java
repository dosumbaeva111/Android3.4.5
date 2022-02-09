package com.example.weatherapp.ui.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Prefs;
import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.models.MainResponse;
import com.example.weatherapp.data.repositories.MainRepository;
import com.example.weatherapp.databinding.FragmentWeatherBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private MainResponse weather;

    private WeatherViewModel viewModel;



    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

        viewModel.weatherLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @Override
            public void onChanged(Resource<MainResponse> resource) {
                switch (resource.status) {
                    case SUCCESS: {
                        setupUI(resource.data);

                        break;
                    }
                    case ERROR: {
                        break;
                    }
                    case LOADING: {
                        break;
                    }
                }
            }
        });

    }

    private void setupUI(MainResponse weather) {
        this.weather = weather;
        binding.textCity.setText(weather.getName());
        binding.textHumidityCifry.setText(weather.getMain().getHumidity().toString() + "%");
        Double temp = weather.getMain().getTemp();
        Integer temp1 = temp.intValue();
        binding.textTemp.setText(temp1.toString());
        Double maxTemp = weather.getMain().getTempMax();
        Integer maxTemp1 = maxTemp.intValue();
        binding.maxTemp.setText(maxTemp1.toString() + "С↑");
        Double minTemp = weather.getMain().getTempMin();
        Integer minTemp1 = minTemp.intValue();
        binding.minTemp.setText(minTemp1.toString() + "С↓");
        binding.textPressureCifry.setText(weather.getMain().getPressure().toString() + "mBar");
        Double windSpeed = weather.getWind().getSpeed();
        Integer windSpeed1 = windSpeed.intValue();
        binding.textWindCifry.setText(windSpeed1.toString() + " km/h");
        String a = weather.getWeather().get(0).getIcon();
        Glide.with(binding.imageWeather)
                .load("http://openweathermap.org/img/wn/" + a + "@2x.png").into(binding.imageWeather);
        binding.textSunriseCifry.setText(getDate(weather.getSys().getSunrise(), "hh:mm") + " AM");
        binding.textSunsetCifry.setText(getDate(weather.getSys().getSunset(), "hh:mm") + " PM");
        binding.textDaytimeCifry.setText(getDate(weather.getDt(), "hh:mm"));

    }
    public static String getDate(Integer milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    protected void setupListeners() {



    }

    private String getCity(){
        Prefs prefs = new Prefs(requireContext());
        String a = prefs.getCity();
        return a;
    }

    @Override
    protected void setupViews() {

        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

    }

    @Override
    protected void callRequests() {

        viewModel.getWeather(getCity());

    }
}