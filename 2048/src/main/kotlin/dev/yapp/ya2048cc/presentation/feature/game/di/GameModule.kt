package dev.yapp.ya2048cc.presentation.feature.game.di

import dev.yapp.ya2048cc.presentation.feature.game.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val gameModule = module {
    viewModelOf(::GameViewModel)
}