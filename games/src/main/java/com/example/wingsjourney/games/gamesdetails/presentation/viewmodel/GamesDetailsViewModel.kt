package com.example.wingsjourney.games.gamesdetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.usecase.base.ResultStatus
import com.example.core.domain.model.Game
import com.example.wingsjourney.games.gamesdetails.usecase.GetGameDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesDetailsViewModel @Inject constructor(
    private val gameDetailsUseCase: GetGameDetailsUseCase
): ViewModel() {

    private val _gameDetailsResult = MutableLiveData<ResultStatus<Game>>()
    val gameDetailsResult: LiveData<ResultStatus<Game>> = _gameDetailsResult

    fun getGameDetails(token: String, id: Long) = viewModelScope.launch{
        gameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams(token, id)).collect{
            _gameDetailsResult.value = it
        }
    }
}