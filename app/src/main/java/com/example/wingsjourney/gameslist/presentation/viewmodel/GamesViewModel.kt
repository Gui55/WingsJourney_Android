package com.example.wingsjourney.gameslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wingsjourney.gameslist.domain.model.Game
import com.example.wingsjourney.gameslist.usecase.GetGamesUseCase
import com.example.wingsjourney.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _gamesResult = MutableLiveData<ResultStatus<List<Game>>>()
    val gamesResult: LiveData<ResultStatus<List<Game>>> = _gamesResult

    fun lookForGames() = viewModelScope.launch {
        getGamesUseCase(GetGamesUseCase.GetGamesParams("")).collect{
            _gamesResult.value = it
        }
    }
}