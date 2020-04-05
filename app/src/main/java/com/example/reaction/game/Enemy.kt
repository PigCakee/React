package com.example.reaction.game


class Enemy(val name: String) {
    var reaction = 0L
    var requiredRating = 5
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
        private fun newEnemy(name: String): Enemy {
            val enemy = Enemy(name)
            when (name){
                "John" -> {
                    enemy.reaction = 350L
                    enemy.requiredRating = 5
                }
                "Jack" -> {
                    enemy.reaction = 450L
                    enemy.requiredRating = 15
                }
                "Dave" -> {
                    enemy.reaction = 250L
                    enemy.requiredRating = 20
                }
            }
            return enemy
        }
        fun newEnemyByNumber(number: Int): Enemy{
            val name = when (number){
                0 -> "John"
                1 -> "Jack"
                2 -> "Dave"
                else -> ""
            }
            return newEnemy(name)
        }
    }
}