package com.mguell.ktpokedex.deps

import android.app.Application
import android.content.Context
import com.mguell.ktpokedex.data.repository.PokemonDataRepository
import com.mguell.ktpokedex.data.repository.database.PokemonDatabase
import com.mguell.ktpokedex.data.repository.datasource.PokemonDataStore
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providePokemonRepository(): PokemonRepository {
        return PokemonDataRepository(PokemonDataStore(), PokemonDatabase())
    }
}