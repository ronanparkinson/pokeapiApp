package com.ronanp.pokeapiapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/v2/pokemon/")
    Call<JSONResponce> getJSON();
}