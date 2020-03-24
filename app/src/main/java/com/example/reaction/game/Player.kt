package com.example.reaction.game

class Player (

) {
    var ratingTournament: Int = 0
    var ratingDuels: Int = 0
    var gun: Gun? = null
    var money: Int = 0
    var progress: Int = 0

    override fun toString(): String {
        return gun?.name ?: "ERROR_NO_GUN_NAME"
    }

}

