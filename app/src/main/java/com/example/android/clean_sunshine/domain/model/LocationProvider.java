package com.example.android.clean_sunshine.domain.model;

public interface LocationProvider {
  void requestCurrentLocation(final LocationProviderListener listener);

  interface LocationProviderListener {
    void onLocationRetrieved(Location location);
    void onRetrieveLocationError();
  }
}
