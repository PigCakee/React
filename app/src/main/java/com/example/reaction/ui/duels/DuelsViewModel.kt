package com.example.reaction.ui.duels

import androidx.lifecycle.ViewModel
import com.example.reaction.entities.Enemy
import com.example.reaction.entities.Player
import com.example.reaction.util.livedata.SingleLiveEvent
import com.example.reaction.util.livedata.mutableLiveData


class DuelsViewModel : ViewModel() {
    val milliseconds = 10L
    val vibratorCommand = SingleLiveEvent<Void>()

    val soundCommand = SingleLiveEvent<Void>()

    val playGameCommand = SingleLiveEvent<Void>()

    var enemyNumber = 0

    private var player: Player = Player.getInstance()

    val isLocked = mutableLiveData("Locked")
    val playButtonClickable = mutableLiveData(false)

    fun setUpEnemies(){
        val enemy: Enemy = Enemy.newEnemyByNumber(enemyNumber)
        if (player.ratingDuels >= enemy.requiredRating){
            isLocked.value = "Unlocked"
            playButtonClickable.value = true
        } else {
            isLocked.value = "Locked"
            playButtonClickable.value = false
        }
    }

    fun handlePlayButtonClick(){
        playGameCommand.call()
    }

    fun onRightButtonClick(){
        vibratorCommand.call()
        soundCommand.call()
    }

    fun onLeftButtonClick(){
        vibratorCommand.call()
        soundCommand.call()
    }
}