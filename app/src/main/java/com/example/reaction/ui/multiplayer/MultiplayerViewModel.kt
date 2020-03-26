package com.example.reaction.ui.multiplayer

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MultiplayerViewModel : ViewModel() {

    var readyTextView = ObservableField<String>()

    fun playGame(){
        readyTextView.set("Start")
    }

    fun playRound(){
        readyTextView.set("Ready...")
    }
}