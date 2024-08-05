package dev.yapp.ya2048cc.presentation.model


sealed class ActionUiModel {
    object NewActionUiModel : ActionUiModel()
    object RedoActionUiModel : ActionUiModel()
}