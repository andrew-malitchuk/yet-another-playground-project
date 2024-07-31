package dev.yapp.ya2048cc.data.repository.impl.di

import dev.yapp.ya2048cc.data.repository.HistoryRepository
import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.data.repository.impl.HistoryRepositoryImpl
import dev.yapp.ya2048cc.data.repository.impl.GameRepositoryImpl
import dev.yapp.ya2048cc.data.repository.impl.ScoreRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<HistoryRepository> {
        HistoryRepositoryImpl(
            get()
        )
    }
    single<GameRepository> {
        GameRepositoryImpl(
            get()
        )
    }
    single<ScoreRepository> {
        ScoreRepositoryImpl(
            get()
        )
    }
}