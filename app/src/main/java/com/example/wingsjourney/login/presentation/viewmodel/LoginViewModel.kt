package com.example.wingsjourney.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wingsjourney.login.usecase.LoginUseCase
import com.example.wingsjourney.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _loginResult = MutableLiveData<ResultStatus<Boolean>>()
    val loginResult: LiveData<ResultStatus<Boolean>> = _loginResult

    fun performLogin(user: String, password: String) = viewModelScope.launch {
        loginUseCase(LoginUseCase.LoginParams(user, password)).collect{
            _loginResult.value = it
        }
    }

}