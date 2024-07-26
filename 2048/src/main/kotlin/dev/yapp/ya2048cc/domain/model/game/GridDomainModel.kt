package dev.yapp.ya2048cc.domain.model.game

import dev.yapp.ya2048cc.data.repository.model.GameRepoModel
import dev.yapp.ya2048cc.data.repository.model.GridRepoModel
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel.Companion.toRepo
import kotlin.random.Random


data class GridDomainModel(
    var size: Int = 0,
    var current: MutableList<IntArray> = mutableListOf()
) {

    fun addTile() {
        val options = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (current[i][j] == 0) {
                    options.add(Pair(i, j))
                }
            }
        }
        if (options.size > 0) {
            val (x, y) = options.random()
            current[x][y] = if (Random.nextFloat() <= 0.1) 4 else 2
        }
    }

    fun transpose() {
        val transpose = Array(size) { IntArray(size) { 0 } }
        for (i in 0 until size) {
            for (j in 0 until size) {
                transpose[j][i] = current[i][j]
            }
        }
        for (i in 0 until size) {
            current[i] = transpose[i]
        }
    }

    fun flip() {
        for (i in 0 until size) {
            current[i].reverse()
        }
    }

    fun isEqualTo(second: List<IntArray>): Boolean {
        if (this.size != second.size) return false
        return this.contentEquals(second)
    }

    private fun contentEquals(second: List<IntArray>): Boolean {
        for (i in current.indices) {
            if (!current[i].contentEquals(second[i])) return false
        }
        return true
    }

    fun copyOf() = current.toTypedArray().copyOf().toList()


    companion object {
        fun GridRepoModel.toDomain() = GridDomainModel(
            size = size,
            current = current
        )

        fun GridDomainModel.toRepo() = GridRepoModel(
            size = size,
            current = current
        )
    }

}