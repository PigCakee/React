package com.example.reaction.entities

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tournament

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
