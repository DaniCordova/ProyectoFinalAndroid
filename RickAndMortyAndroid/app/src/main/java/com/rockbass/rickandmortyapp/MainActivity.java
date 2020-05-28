package com.rockbass.rickandmortyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int page=1;
    private boolean loading;
    private CharacterAdapter characterAdapter;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview_characters);
        characterAdapter= new CharacterAdapter(this);
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    int visibleItemCount=layoutManager.getChildCount();
                    int totalItemCount=layoutManager.getItemCount();
                  int pastItem=layoutManager.findFirstVisibleItemPosition();

                    if(loading){
                        if(visibleItemCount+pastItem>=totalItemCount){
                            //Cargo mas paginas
                            loading=false;
                            page++;
                            ObtenerPersonajes(page);
                        }

                    }
                }
            }
        });

        loading=true;
        ObtenerPersonajes(page);
    }
        public void ObtenerPersonajes(int page) {

            RetrofitGenerator.getCharacterService().getCharacters(page)
                    .enqueue(new Callback<ResultCharacter>() {
                        @Override
                        public void onResponse(Call<ResultCharacter> call, Response<ResultCharacter> response) {
                            loading=true;
                            if (response.isSuccessful()){

                                if(response.body()!=null){
                                    characterAdapter.addResults(response.body().results);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResultCharacter> call, Throwable t) {
                            loading=true;
                            Log.e(TAG, t.getMessage(), t);
                        }
                    });
        }




}
