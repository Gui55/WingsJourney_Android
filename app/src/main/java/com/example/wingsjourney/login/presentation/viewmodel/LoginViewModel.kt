package com.example.wingsjourney.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.usecase.base.ResultStatus
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.example.wingsjourney.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _loginResult = MutableLiveData<ResultStatus<LoginResponse>>()
    val loginResult: LiveData<ResultStatus<LoginResponse>> = _loginResult

    fun performLogin(user: String, password: String) = viewModelScope.launch {
        loginUseCase(LoginUseCase.LoginParams(LoginRequest(user, password))).collect{
            _loginResult.value = it
        }
    }

}