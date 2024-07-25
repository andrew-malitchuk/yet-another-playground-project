package dev.app.io

import android.app.Application
import android.content.Context
import dev.yapp.ya2048cc.data.repository.impl.di.repositoryModule
import dev.yapp.ya2048cc.data.source.preferences.impl.di.preferencesModule
import dev.yapp.ya2048cc.domain.impl.di.domainModule
import dev.yapp.ya2048cc.presentation.feature.game.di.gameModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class YappApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        extracted(this)
    }

    private fun extracted(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    preferencesModule,
                    repositoryModule,
                    domainModule,
                    gameModule
                )
            )
        }
    }

}

