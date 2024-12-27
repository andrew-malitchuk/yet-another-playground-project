package dev.yapp.onboarding.yado.core

sealed class YadoInternalState {
    // init bitmaps
    object Init : YadoInternalState()

    // inner calculations
    object InProgress : YadoInternalState()

    // find next target
    object Next : YadoInternalState()

    // show original content
    object Idle : YadoInternalState()
}