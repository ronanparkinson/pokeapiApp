package com.ronanp.pokeapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import adapter.DataAdapter;
import model.Pokemon;
import model.PokemonResponce;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private java.util.List<model.Pokemon> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);

        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface request = retrofit.create(ApiInterface.class);
        Call<PokemonResponce> call = request.getJSON();
        call.enqueue(new Callback<PokemonResponce>() {
            @Override
            public void onResponse(Call<PokemonResponce> call, Response<PokemonResponce> response) {
                  PokemonResponce pokemonResponce = response.body();
                  if (pokemonResponce != null) {
                      adapter.refreshData(pokemonResponce.getResults());
                  }
            }

            @Override
            public void onFailure(Call<PokemonResponce> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}