package com.mguell.ktpokedex.data.entity.local

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class PokemonRealm(
    var name: String? = null,
    var height: Int = 0,
    var weight: Int = 0,
    var id: Int = 0,
    var posterPath: String? = null,
    var order: Int = 0,
    var baseExperience: Int = 0,
    var types: RealmList<String>? = null,
    var catchedDate: String? = null
) : RealmObject()