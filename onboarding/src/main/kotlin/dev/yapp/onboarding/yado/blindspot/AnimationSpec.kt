package dev.yapp.onboarding.yado.blindspot

data class AnimationSpec(
    val duration: Int = 1_000
)

@DslMarker
annotation class AnimationSpecDsl

@AnimationSpecDsl
class AnimationSpecBuilder {
    var duration: Int = 0

    fun build(): AnimationSpec = AnimationSpec(
        duration = duration,
    )
}

fun animationSpec(block: AnimationSpecBuilder.() -> Unit): AnimationSpec {
    return AnimationSpecBuilder().apply(block).build()
}