package com.mguell.ktpokedex

import com.mguell.ktpokedex.data.entity.remote.PokemonEntity
import com.mguell.ktpokedex.data.entity.remote.SpritesEntity
import com.mguell.ktpokedex.data.entity.remote.TypeEntity
import com.mguell.ktpokedex.data.entity.remote.TypeNameEntity
import com.mguell.ktpokedex.data.repository.PokemonDataRepository
import com.mguell.ktpokedex.data.repository.database.PokemonDatabase
import com.mguell.ktpokedex.data.repository.datasource.PokemonDataStore
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PokemonDataRepositoryTest {

    @Mock
    private lateinit var networkPokemonDataStore: PokemonDataStore

    @Mock
    private lateinit var pokemonDatabase: PokemonDatabase

    private lateinit var pokemonDataRepository: PokemonRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        pokemonDataRepository = PokemonDataRepository(networkPokemonDataStore, pokemonDatabase)
    }

    @Test
    fun should_return_a_list_with_pokemon() {
        val pokemonByIdResponse = givenPokemon()
        `when`(networkPokemonDataStore.pokemonById(anyInt())).thenReturn(
            Observable.just(
                pokemonByIdResponse
            )
        )

        pokemonDataRepository.pokemonById(anyInt())
            .test()
            .assertComplete()
    }

    private fun givenPokemon(): PokemonEntity {
        val bulbasaurTypes: ArrayList<TypeEntity> = ArrayList()
        bulbasaurTypes.add(TypeEntity(TypeNameEntity("poison")))
        bulbasaurTypes.add(TypeEntity(TypeNameEntity("grass")))
        return PokemonEntity(
            "Bulbasaur",
            7,
            69,
            1,
            SpritesEntity("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            1,
            64,
            bulbasaurTypes
        )
    }
}