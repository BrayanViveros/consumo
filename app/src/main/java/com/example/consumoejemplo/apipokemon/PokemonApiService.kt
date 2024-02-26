package com.example.consumoejemplo.apipokemon

import com.example.consumoejemplo.Pokemon
import com.example.consumoejemplo.PokemonRespuesta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
   // @GET("pokemon/{id}")
    //fun  getPokemonById(@Path("id")id:Int): Call<Pokemon>

    @GET("pokemon")fun getPokemonById(): Call<PokemonRespuesta>



}