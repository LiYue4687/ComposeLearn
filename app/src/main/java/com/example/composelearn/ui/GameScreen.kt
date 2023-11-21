package com.example.composelearn.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composelearn.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    var gameUiState = gameViewModel.uiState.collectAsState()
    var userGuess = gameViewModel.userGuess

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameMainLayout(
            currentScrambledWord = "sseuG",
            userGuess = userGuess,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameMainLayout(currentScrambledWord: String,
                   userGuess:String, 
                   onUserGuessChanged: (String) -> Unit,
                   onKeyboardDone: () -> Unit,
                   modifier: Modifier = Modifier
                   ){
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.game_guess_welcome))
        Text(text = "$currentScrambledWord")
        OutlinedTextField(
            value = userGuess,
            onValueChange = onUserGuessChanged,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )
    }

}