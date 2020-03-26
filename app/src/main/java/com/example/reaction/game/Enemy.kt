package com.example.reaction.game

class Enemy(val name: String) {
    var reaction = 0.0
    var icon = 0 //TODO реализовать подгрузку иконок
    var requiredRating = 0
    var isLocked = false
    var gun: Gun? = null
    var reward = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Enemy

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}