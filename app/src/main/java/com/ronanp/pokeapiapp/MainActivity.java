package com.ronanp.pokeapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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

public class MainActivity extends AppCompatActivity implements DataAdapter.onItemClickListener{
    public static final String Name = "name";
    public static final String URL = "url";
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
                  adapter.setOnItemClickListener(MainActivity.this);
            }

            @Override
            public void onFailure(Call<PokemonResponce> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ApiInterface.class);
        Pokemon pokemon = data.get(position);
        intent.putExtra(Name, pokemon.getName());
        intent.putExtra(URL, pokemon.getUrl());

        startActivity(intent);
    }
}