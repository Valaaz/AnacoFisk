package com.ihm.anacofisk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ihm.anacofisk.model.Game

class GameViewModel : ViewModel() {
    var game by mutableStateOf(Game(economy = 50, pleasure = 50))
        private set

    fun increaseEconomy(amount: Int) {
        game = if (game.economy + amount > 100) {
            game.copy(economy = 100)
        } else {
            game.copy(economy = game.economy + amount)
        }
    }

    fun decreaseEconomy(amount: Int) {
        game = if (game.economy - amount < 0) {
            game.copy(economy = 0)
        } else {
            game.copy(economy = game.economy - amount)
        }
    }

    fun increasePleasure(amount: Int) {
        game = if (game.pleasure + amount > 100) {
            game.copy(pleasure = 100)
        } else {
            game.copy(pleasure = game.pleasure + amount)
        }
    }

    fun decreasePleasure(amount: Int) {
        game = if (game.pleasure - amount < 0) {
            game.copy(pleasure = 0)
        } else {
            game.copy(pleasure = game.pleasure - amount)
        }
    }

}
