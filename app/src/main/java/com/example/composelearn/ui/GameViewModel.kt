package com.example.composelearn.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel :ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private var usedWords:MutableSet<String> = mutableSetOf()
    private lateinit var currentWord:String


    init {
        resetGame()
    }

    private fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState()
        currentWord = getRandomWord()
    }

    private fun getRandomWord():String {
        if(_uiState.value.currentWordCount==1) {
            _uiState.update {currentState ->
                currentState.copy("sseuG",1)
            }
        }
        else{
            _uiState.update {currentState ->
                currentState.copy("domRan",2)
            }
        }
        return _uiState.value.currentScrambledWord
    }

    fun updateUserGuess(newGuess: String){
        userGuess = newGuess
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == 2){
            //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else{
            // Normal round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = getRandomWord(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    fun checkUserGuess(){
        Log.i("myTest", "$userGuess, $currentWord")
        if (userGuess.equals(currentWord, ignoreCase = false)) {
            // User's guess is correct, increase the score
            // and call updateGameState() to prepare the game for next round
            val updatedScore = _uiState.value.score.plus(10)
            updateGameState(updatedScore)
            Log.i("myTest", "success")
        } else {
            // User's guess is wrong, show an error
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
            Log.i("myTest", "wrong")
        }
        // Reset user guess
        updateUserGuess("")
    }

}