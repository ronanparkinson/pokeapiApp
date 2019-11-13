package com.ronanp.pokeapiapp;

import model.PokemonResponce;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/v2/pokemon/")
    Call<PokemonResponce> getJSON();
}