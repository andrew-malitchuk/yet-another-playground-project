package dev.yapp.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.yapp.onboarding.yado.YadoContent
import dev.yapp.onboarding.yado.core.rememberYadoState

class OnboardingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val yadoState = rememberYadoState()

            DemoContent(yadoState) {
                YadoContent(
                    it,
                    yadoState
                )
            }
        }
    }
}