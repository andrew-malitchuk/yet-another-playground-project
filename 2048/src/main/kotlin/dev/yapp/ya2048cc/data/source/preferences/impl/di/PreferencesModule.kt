package dev.yapp.ya2048cc.data.source.preferences.impl.di

import dev.yapp.ya2048cc.data.source.preferences.HistoryPreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.GamePreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.ScorePreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.impl.HistoryPreferencesDataSourceImpl
import dev.yapp.ya2048cc.data.source.preferences.impl.GamePreferencesDataSourceImpl
import dev.yapp.ya2048cc.data.source.preferences.impl.ScorePreferencesDataSourceImpl
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.BarDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.FooDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.HistoryDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.GameDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.ScoreDao
import org.koin.dsl.module

val preferencesModule = module {

    single { HistoryDao(get()) }
    single { GameDao(get()) }
    single { ScoreDao(get()) }
    single { FooDao(get()) }
    single { BarDao(get()) }

    single<HistoryPreferencesDataSource> {
        HistoryPreferencesDataSourceImpl(
            get(),
        )
    }

    single<GamePreferencesDataSource> {
        GamePreferencesDataSourceImpl(
            get(),
        )
    }

    single<ScorePreferencesDataSource> {
        ScorePreferencesDataSourceImpl(
            get(),
        )
    }

}