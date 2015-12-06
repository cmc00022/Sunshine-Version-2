/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.clean_sunshine.app.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.android.clean_sunshine.app.R;

public class DetailActivity extends AppCompatActivity {

  private static final String WEATHER_ID_INTENT_EXTRA = "WEATHER_ID_INTENT_EXTRA";

  public static void startActivity(Context context, int weatherId) {
    Intent intent = new Intent(context, DetailActivity.class);
    intent.putExtra(WEATHER_ID_INTENT_EXTRA, weatherId);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.

      int weatherId = getIntent().getIntExtra(WEATHER_ID_INTENT_EXTRA, 0);
      getSupportFragmentManager().beginTransaction()
          .add(R.id.weather_detail_container, DetailFragment.newInstance(weatherId))
          .commit();
    }
  }
}