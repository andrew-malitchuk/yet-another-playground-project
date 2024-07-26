package dev.yapp.ya2048cc.domain.impl.di

import dev.yapp.ya2048cc.domain.impl.score.GetScoreUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.move.UndoMoveUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.game.GetGameUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.game.IsGameOverUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.game.IsGameWonUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.game.NewGameUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.game.RestartUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.state.SavePreviousStateUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.game.SwipeScenarioImpl
import dev.yapp.ya2048cc.domain.impl.move.OnMoveUseCaseImpl
import dev.yapp.ya2048cc.domain.impl.score.UpdateScoreUseCaseImpl
import dev.yapp.ya2048cc.domain.usecase.score.GetScoreUseCase
import dev.yapp.ya2048cc.domain.usecase.move.UndoMoveUseCase
import dev.yapp.ya2048cc.domain.usecase.game.GetGameUseCase
import dev.yapp.ya2048cc.domain.usecase.game.IsGameOverUseCase
import dev.yapp.ya2048cc.domain.usecase.game.IsGameWonUseCase
import dev.yapp.ya2048cc.domain.usecase.game.NewGameUseCase
import dev.yapp.ya2048cc.domain.usecase.game.RestartUseCase
import dev.yapp.ya2048cc.domain.usecase.state.SavePreviousStateUseCase
import dev.yapp.ya2048cc.domain.usecase.game.SwipeScenario
import dev.yapp.ya2048cc.domain.usecase.move.OnMoveUseCase
import dev.yapp.ya2048cc.domain.usecase.score.UpdateScoreUseCase
import org.koin.dsl.module

val domainModule = module {
    single<GetGameUseCase> {
        GetGameUseCaseImpl(
            get(),
        )
    }
    single<IsGameOverUseCase> {
        IsGameOverUseCaseImpl(
            get(),
        )
    }
    single<IsGameWonUseCase> {
        IsGameWonUseCaseImpl(
            get(),
        )
    }
    single<NewGameUseCase> {
        NewGameUseCaseImpl(
            get(),
            get(),
            get(),
        )
    }
    single<RestartUseCase> {
        RestartUseCaseImpl(
            get(),
        )
    }
    single<SwipeScenario> {
        SwipeScenarioImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
    single<GetScoreUseCase> {
        GetScoreUseCaseImpl(
            get()
        )
    }
    single<UndoMoveUseCase> {
        UndoMoveUseCaseImpl(
            get(),
            get(),
            get(),
        )
    }
    single<UpdateScoreUseCase> {
        UpdateScoreUseCaseImpl(
            get(),
        )
    }
    single<OnMoveUseCase> {
        OnMoveUseCaseImpl(
            get(),
        )
    }
    single<SavePreviousStateUseCase> {
        SavePreviousStateUseCaseImpl(
            get(),
            get(),
        )
    }
}