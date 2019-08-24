package com.mguell.ktpokedex.data.repository.database

import com.mguell.ktpokedex.data.entity.local.PokemonRealm
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort

class PokemonDatabase : APokemonDatabase<PokemonRealm>(PokemonRealm::class.java) {

    fun savePokemon(pokemonRealm: PokemonRealm) {
        this.save(pokemonRealm)
    }

    fun getBackpack(): Observable<List<PokemonRealm>> {
        Realm.getDefaultInstance().use { realm ->
            val result =
                realm.where(PokemonRealm::class.java).sort("order", Sort.ASCENDING).findAll()
            result.let {
                return Observable.fromArray(realm.copyFromRealm(it))
            }
        }
    }
}
