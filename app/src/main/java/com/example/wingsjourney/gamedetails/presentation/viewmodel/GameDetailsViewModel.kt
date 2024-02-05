package com.example.wingsjourney.gamedetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wingsjourney.gamedetails.domain.model.Game
import com.example.wingsjourney.gamedetails.usecase.GetGameDetailsUseCase
import com.example.wingsjourney.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
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