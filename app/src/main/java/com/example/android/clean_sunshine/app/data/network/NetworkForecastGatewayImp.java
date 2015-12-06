package com.example.android.clean_sunshine.app.data.network;

import android.content.Context;
import com.example.android.clean_sunshine.app.Utility;
import com.example.android.clean_sunshine.app.domain.model.Forecast;
import com.example.android.clean_sunshine.app.domain.model.NetworkForecastGateway;
import com.example.android.clean_sunshine.app.data.network.model.ApiForecast;
import com.example.android.clean_sunshine.app.data.network.model.ApiForecastMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.clean_sunshine.app.BuildConfig.OPEN_WEATHER_MAP_API_KEY;

public class NetworkForecastGatewayImp implements NetworkForecastGateway {

  private final Context context;
  private final OpenWeatherApiClient apiClient;
  private final ApiForecastMapper apiForecastMapper = new ApiForecastMapper();

  public NetworkForecastGatewayImp(Context context, OpenWeatherApiClient apiClient) {
    this.context = context;
    this.apiClient = apiClient;
  }

  @Override public List<Forecast> refresh() {
    String locationQuery = Utility.getPreferredLocation(context);
    try {
      ApiForecast apiForecast =
          apiClient.dailyForecast(OPEN_WEATHER_MAP_API_KEY, "metric", locationQuery,
              String.valueOf("14")).execute().body();
      return apiForecastMapper.mapFromApi(apiForecast, locationQuery);
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}