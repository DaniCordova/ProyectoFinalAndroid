package com.rockbass.rickandmortyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeActivity extends AppCompatActivity {
    TextView textViewName, textViewType, textViewDimension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ExpandableListAdapter listAdapter;
        ExpandableListView expandableListView;

        List<String> episodeList = new ArrayList<String>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_activity);
        Intent intent = getIntent();
        expandableListView = (ExpandableListView) findViewById(R.id.Expandable_episodes);

        textViewName = this.findViewById(R.id.textview_name);
        textViewDimension = this.findViewById(R.id.textView_dimension);
        textViewType = this.findViewById(R.id.textView_type);
        String url = intent.getStringExtra("url");
        ObtenerEpisode(url);
    }

    public void ObtenerEpisode(String url)  {
        RetrofitGenerator.getEpisodeService().getEpisodeByUrl(url)
                .enqueue(new Callback<EpisodeResult>() {

                    @Override
                    public void onResponse(Call<EpisodeResult> call, Response<EpisodeResult> response) {

                        if (response.isSuccessful()){

                            if(response.body()!=null) {
                                textViewName.setText(response.body().name);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<EpisodeResult> call, Throwable t) {

                    }
                });
    }


}
