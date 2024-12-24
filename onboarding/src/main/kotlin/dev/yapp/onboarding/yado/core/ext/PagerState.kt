package dev.yapp.onboarding.yado.core.ext

import androidx.compose.foundation.pager.PagerState

fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}