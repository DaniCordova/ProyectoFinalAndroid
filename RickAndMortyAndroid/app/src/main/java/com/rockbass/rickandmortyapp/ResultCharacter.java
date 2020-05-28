package com.rockbass.rickandmortyapp;

import java.util.List;

public class ResultCharacter{

    static class Result{
        public int id;
        public String name;
        public String status;
        public String species;
        public String type;
        public String gender;
        public String image;
        public String url;
        public Location location;
    }

    public List<Result> results;
}
