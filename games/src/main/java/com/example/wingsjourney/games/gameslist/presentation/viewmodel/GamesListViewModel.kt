package com.example.wingsjourney.games.gameslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.usecase.base.ResultStatus
import com.example.core.domain.model.GeneralGameInfo
import com.example.wingsjourney.games.gameslist.usecase.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _gamesResult = MutableLiveData<ResultStatus<List<GeneralGameInfo>>>()
    val gamesResult: LiveData<ResultStatus<List<GeneralGameInfo>>> = _gamesResult

    fun lookForGames(token: String) = viewModelScope.launch {
        getGamesUseCase(GetGamesUseCase.GetGamesParams(token)).collect{
            _gamesResult.value = it
        }
    }
}