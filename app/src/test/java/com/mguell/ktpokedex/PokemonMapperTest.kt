package com.mguell.ktpokedex

import com.mguell.ktpokedex.data.entity.PokemonMapper
import com.mguell.ktpokedex.data.entity.local.PokemonRealm
import com.mguell.ktpokedex.data.entity.remote.PokemonEntity
import com.mguell.ktpokedex.data.entity.remote.SpritesEntity
import com.mguell.ktpokedex.data.entity.remote.TypeEntity
import com.mguell.ktpokedex.data.entity.remote.TypeNameEntity
import com.mguell.ktpokedex.domain.model.Pokemon
import io.realm.RealmList
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PokemonMapperTest {

    private lateinit var pokemonMapper: PokemonMapper

    private lateinit var testPokemon: Pokemon

    @Before
    fun setUp() {
        pokemonMapper = PokemonMapper()
        val testPokemonTypes = ArrayList<String>()
        testPokemonTypes.add("poison")
        testPokemonTypes.add("grass")
        testPokemon = Pokemon(
            "Bulbasaur",
            7,
            69,
            1,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            1,
            64,
            testPokemonTypes,
            ""
        )
    }

    @Test
    fun should_convert_pokemon_entity_into_pokemon() {
        val testPokemonTypes: ArrayList<TypeEntity> = ArrayList()
        testPokemonTypes.add(TypeEntity(TypeNameEntity(testPokemon.types[0])))
        testPokemonTypes.add(TypeEntity(TypeNameEntity(testPokemon.types[1])))
        val pokemonEntity = PokemonEntity(
            testPokemon.name,
            testPokemon.height,
            testPokemon.weight,
            testPokemon.id,
            SpritesEntity(testPokemon.posterPath),
            testPokemon.order,
            testPokemon.baseExperience,
            testPokemonTypes
        )
        assert(pokemonMapper.transform(pokemonEntity) == testPokemon)
    }

    @Test
    fun should_convert_realm_pokemon_list_into_pokemon_list() {
        val pokemonRealmTypes = RealmList<String>()
        pokemonRealmTypes.addAll(testPokemon.types)
        val pokemonRealm = PokemonRealm(
            testPokemon.name,
            testPokemon.height,
            testPokemon.weight,
            testPokemon.id,
            testPokemon.posterPath,
            testPokemon.order,
            testPokemon.baseExperience,
            pokemonRealmTypes,
            testPokemon.catchedTime
        )
        val pokemonRealmList = ArrayList<PokemonRealm>()
        pokemonRealmList.add(pokemonRealm)
        val pokemonList = pokemonMapper.transformRealmList(pokemonRealmList)
        assert(pokemonList.size == pokemonRealmList.size && pokemonList.size == 1)
        assert(pokemonList.first() == testPokemon)
    }

    @Test
    fun should_convert_pokemon_into_realm_pokemon() {
        val realmPokemon: PokemonRealm = pokemonMapper.transform(testPokemon)
        assert(realmPokemon.name.equals(testPokemon.name))
        assert(realmPokemon.height == testPokemon.height)
        assert(realmPokemon.weight == testPokemon.weight)
        assert(realmPokemon.id == testPokemon.id)
        assert(realmPokemon.posterPath.equals(testPokemon.posterPath))
        assert(realmPokemon.order == testPokemon.order)
        assert(realmPokemon.baseExperience == testPokemon.baseExperience)
        assert(realmPokemon.types!! == testPokemon.types)
        assert(realmPokemon.catchedDate.equals(testPokemon.catchedTime))
    }
}