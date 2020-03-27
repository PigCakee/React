package com.example.reaction.game

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.reaction.R

class Enemy(val name: String, context: Context) {
    var reaction = 0L
    var icon: Drawable? = context.getDrawable(R.drawable.speed64)//TODO реализовать подгрузку иконок
    var requiredRating = 0
    var isLocked = false
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

    companion object Factory {
        fun newEnemy(name: String, context: Context): Enemy {
            val enemy = Enemy(name, context)
            when (name){
                "John" -> {
                    enemy.reaction = 350L
                    enemy.icon = context.getDrawable(R.drawable.bandit_john)
                }
                "Jack" -> {
                    enemy.reaction = 450L
                    enemy.icon = context.getDrawable(R.drawable.bandit_jack)
                }
                "Dave" -> {
                    enemy.reaction = 250L
                    enemy.icon = context.getDrawable(R.drawable.bandit_dave)
                }
            }
            return enemy
        }
    }
}