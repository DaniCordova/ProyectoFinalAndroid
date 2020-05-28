package com.rockbass.rickandmortyapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGenerator {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Retrofit retrofitLocation = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Retrofit retrofitEpisode = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Character character;

    public static Character getCharacterService(){
        if (character == null){
            character = retrofit.create(Character.class);
        }

        return character;
    }

    private static Location1 location;

    public static Location1 getLocationService(){
        if (location == null){
            location = retrofitLocation.create(Location1.class);
        }

        return location;
    }

    private static Episode1 episode;

    public static Episode1 getEpisodeService(){
        if(episode == null){
            episode = retrofitEpisode.create(Episode1.class);
        }

        return episode;
    }

}
