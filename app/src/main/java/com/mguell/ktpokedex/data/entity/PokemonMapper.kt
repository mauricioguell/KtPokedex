package com.mguell.ktpokedex.data.entity

import com.mguell.ktpokedex.data.entity.local.PokemonRealm
import com.mguell.ktpokedex.data.entity.remote.PokemonEntity
import com.mguell.ktpokedex.data.entity.remote.TypeEntity
import com.mguell.ktpokedex.domain.model.Pokemon
import io.realm.RealmList

class PokemonMapper {

    fun transform(pokemonEntity: PokemonEntity): Pokemon {
        return Pokemon(
            capitalizeFirstLetter(pokemonEntity.name),
            pokemonEntity.height,
            pokemonEntity.weight,
            pokemonEntity.id,
            pokemonEntity.sprites.frontDefaultPath,
            pokemonEntity.order,
            pokemonEntity.baseExperience,
            entityTypesToTypes(pokemonEntity.types),
            ""
        )
    }

    fun transformRealmList(realmList: List<PokemonRealm>): List<Pokemon> {
        val backpack = ArrayList<Pokemon>()
        for (realmPokemon in realmList) {
            backpack.add(
                Pokemon(
                    realmPokemon.name!!,
                    realmPokemon.height,
                    realmPokemon.weight,
                    realmPokemon.id,
                    realmPokemon.posterPath!!,
                    realmPokemon.order,
                    realmPokemon.baseExperience,
                    realmTypesToTypes(realmPokemon.types!!),
                    realmPokemon.catchedDate!!
                )
            )
        }
        return backpack
    }

    fun transform(pokemon: Pokemon): PokemonRealm {
        return PokemonRealm(
            pokemon.name,
            pokemon.height,
            pokemon.weight,
            pokemon.id,
            pokemon.posterPath,
            pokemon.order,
            pokemon.baseExperience,
            typesToRealmTypes(pokemon.types),
            pokemon.catchedTime
        )
    }

    private fun entityTypesToTypes(types: ArrayList<TypeEntity>): ArrayList<String> {
        val parsedTypes: ArrayList<String> = ArrayList()
        for (type in types) {
            parsedTypes.add(type.typeName.name)
        }
        return parsedTypes
    }

    private fun typesToRealmTypes(types: ArrayList<String>): RealmList<String> {
        val parsedTypes: RealmList<String> = RealmList()
        parsedTypes.addAll(types)
        return parsedTypes
    }

    private fun realmTypesToTypes(types: RealmList<String>): ArrayList<String> {
        val parsedTypes: ArrayList<String> = ArrayList()
        parsedTypes.addAll(types)
        return parsedTypes
    }

    private fun capitalizeFirstLetter(pokemonName: String): String {
        val sb = StringBuilder(pokemonName)
        sb.setCharAt(0, Character.toUpperCase(sb[0]))
        return sb.toString()
    }
}