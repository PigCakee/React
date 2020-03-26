package com.example.reaction.game

class Tournament(val name: String) {
    var requiredRating = 0
    var reward: Triple<Int, Int, Int> = Triple(0,0,0)
    var reactionTimeRequired: Triple<Int, Int, Int> = Triple(0,0,0)

    companion object Factory{
        fun newTournament(name: String): Tournament {
            val tournament = Tournament(name)
            when (name){
                "" -> { //TODO implement names
                    tournament.requiredRating = 0
                    tournament.reward = Triple(0,0,0)
                    tournament.reactionTimeRequired = Triple(0,0,0)
                }
            }
            return tournament
        }
    }

    fun rewardPlayer(player: Player, reactionTime: Long){
        if (reactionTime <= reactionTimeRequired.first) player.money += reward.first
        if (reactionTime <= reactionTimeRequired.second) player.money += reward.second
        if (reactionTime <= reactionTimeRequired.third) player.money += reward.third
    }
}
