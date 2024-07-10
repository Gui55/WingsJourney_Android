package com.example.wingsjourney.login.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.usecase.base.ResultStatus
import com.example.testingutils.MainCoroutineRule
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.example.wingsjourney.login.usecase.LoginUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: LoginViewModel

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup(){
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `viewModel method should return login success`() =
        runTest{
            //Arrange
            whenever(loginUseCase(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            LoginResponse("token")
                        )
                    )
                )

            //Act
            viewModel.performLogin("user", "password")

            //Assert
            val result = viewModel.loginResult.value
            assertTrue(result is ResultStatus.Success)
        }

}