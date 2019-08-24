package com.mguell.ktpokedex.data.repository.database

import io.realm.Realm
import io.realm.RealmObject


abstract class APokemonDatabase<T : RealmObject>(private val clazz: Class<T>) {

    fun save(item: T, realm: Realm? = null) {
        if (realm == null) {
            Realm.getDefaultInstance().use { realmInstance ->
                realmInstance.executeTransaction {
                    realmInstance.insertOrUpdate(item)
                }
            }
        } else {
            realm.insertOrUpdate(item)
        }
    }

    fun getAll(realm: Realm = Realm.getDefaultInstance()): MutableList<T>? {
        return realm.copyFromRealm(realm.where(clazz).findAll())
    }
}
