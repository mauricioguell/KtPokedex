package com.mguell.ktpokedex.data.net

import com.mguell.ktpokedex.data.entity.remote.PokemonEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokemonService {

    /**
     * Gets a Pokemon from the POKE API by its id.
     *
     * @param id Int with the unique identification of a Pokemon of the POKE API.
     * @return Observable<PokemonEntity> call with the Pokemon with that id
     */
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("pokemon/{id}/")
    fun pokemonById(
        @Path("id") id: Int
    ): Observable<PokemonEntity>
}