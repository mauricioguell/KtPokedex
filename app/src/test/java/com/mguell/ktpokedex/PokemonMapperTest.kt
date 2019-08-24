package com.mguell.ktpokedex

import com.mguell.ktpokedex.data.entity.PokemonMapper
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PokemonMapperTest {

    private lateinit var pokemonMapper: PokemonMapper

    @Before
    fun setUp() {
        pokemonMapper = PokemonMapper()
    }
}