package com.ronanp.pokeapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import adapter.DataAdapter;
import model.AndroidVersion;
import model.ApiInterface;
import model.JSONResponce;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<JSONResponce> call = request.getJSON();
        call.enqueue(new Callback<JSONResponce>() {
            @Override
            public void onResponse(Call<JSONResponce> call, Response<JSONResponce> response) {

                JSONResponce jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponce> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
