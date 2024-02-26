package com.example.consumoejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consumoejemplo.apipokemon.PokemonApiService
import com.example.consumoejemplo.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivityMainBinding //traer cada identificar que esta en el xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = binding.image

        val text = binding.textView


        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokemonApiService::class.java)

        val call = service.getPokemonById()//Consulta el pokemon con ID 1

        call.enqueue(object : Callback<PokemonRespuesta> {

            override fun onResponse(
                call: Call<PokemonRespuesta>,response: Response<PokemonRespuesta>) {
                if (response.isSuccessful){
                    val pokemon:PokemonRespuesta?=response.body()
                    var listaPokemones=ArrayList<Pokemon>()
                    listaPokemones = (pokemon?.results?:
                    Toast.makeText(this@MainActivity,"el consumido es $listaPokemones",Toast.LENGTH_SHORT).show())as ArrayList<Pokemon>
                    text.text= pokemon.toString()


                    Picasso.get()
                        .load("https://centrodepsicologiademadrid.es/wp-content/uploads/2018/11/rabiaytristeza.jpg")
                        .into(image);


                }

            }

            override fun onFailure(call: Call<PokemonRespuesta>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }


        })


    }
}