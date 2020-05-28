package com.rockbass.rickandmortyapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Episode1 {
    @GET
    Call<EpisodeResult> getEpisodeByUrl(@Url String url);
}
