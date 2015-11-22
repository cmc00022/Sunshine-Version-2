package com.example.android.clean_sunshine.app.data.remote;

import com.example.android.clean_sunshine.app.data.domain.Forecast;
import com.example.android.clean_sunshine.app.data.domain.Location;
import java.util.ArrayList;
import java.util.List;

public class ApiForecastMapper {

  public List<Forecast> mapFromApi(ApiForecast apiForecast, String locationQuery) {
    List<Forecast> list = new ArrayList<>(apiForecast.getItems().size());
    for (ApiForecastItem item : apiForecast.getItems()) {
      list.add(mapForecastItem(item, apiForecast.getCity(), locationQuery));
    }
    return list;
  }

  private Forecast mapForecastItem(ApiForecastItem item, ApiCity city, String locationQuery) {
    return new Forecast.Builder().id(mapId(item))
        .description(mapDescription(item))
        .high(mapMaxTemperature(item.getTemperature()))
        .low(mapMinTemperature(item.getTemperature()))
        .humidity(item.getHumidity())
        .pressure(item.getPressure())
        .location(mapLocation(city, locationQuery))
        .build();
  }

  private Location mapLocation(ApiCity apiCity, String locationQuery) {
    return new Location.Builder().cityName(apiCity.getName())
        .lat(apiCity.getCoord().getLat())
        .lon(apiCity.getCoord().getLon())
        .locationSetting(locationQuery)
        .build();
  }

  private double mapMinTemperature(ApiTemperature apiTemperature) {
    return apiTemperature.getMin();
  }

  private double mapMaxTemperature(ApiTemperature apiTemperature) {
    return apiTemperature.getMax();
  }

  private String mapDescription(ApiForecastItem item) {
    return item.getWeatherList().get(0).getDescription();
  }

  private int mapId(ApiForecastItem item) {
    return item.getWeatherList().get(0).getId();
  }
}
