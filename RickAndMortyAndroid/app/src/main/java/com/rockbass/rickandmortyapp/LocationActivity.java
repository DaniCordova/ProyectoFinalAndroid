package com.rockbass.rickandmortyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {

    TextView textViewName, textViewType, textViewDimension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent intent = getIntent();
        textViewName = this.findViewById(R.id.textview_name);
        textViewDimension = this.findViewById(R.id.textView_dimension);
        textViewType = this.findViewById(R.id.textView_type);
        String url = intent.getStringExtra("url");
        ObtenerLocation(url);
    }

    public void ObtenerLocation(String url)  {

        RetrofitGenerator.getLocationService().getLocationByUrl(url)
                .enqueue(new Callback<LocationResult>() {

                    @Override
                    public void onResponse(Call<LocationResult> call, Response<LocationResult> response) {

                        if (response.isSuccessful()){

                            if(response.body()!=null) {
                                textViewName.setText(response.body().name);
                                textViewDimension.setText(response.body().dimension);
                                textViewType.setText(response.body().type);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LocationResult> call, Throwable t) {

                    }
                });
    }


}
