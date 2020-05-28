package com.rockbass.rickandmortyapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Location1 {
        @GET
        Call<LocationResult> getLocationByUrl(@Url String url);
}
