package com.example.wingsjourney.login.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.usecase.base.ResultStatus
import com.example.testingutils.MainCoroutineRule
import com.example.wingsjourney.login.data.repository.LoginRepository
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var loginRepository: LoginRepository

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = LoginUseCaseImpl(loginRepository)
    }

    @Test
    fun `check if useCase really starts with loading status`() =
        runTest{
            //Arrange
            whenever(loginRepository.login(any()))
                .thenReturn(LoginResponse("token"))

            //Act
            val result = loginUseCase(LoginUseCase.LoginParams(LoginRequest("user", "password")))
                .toList()

            //Assert
            assert(result[0] is ResultStatus.Loading)
        }

    @Test
    fun `check if repository is really being called`() =
        runTest {

            //Arrange
            val request = LoginRequest("user", "password")

            whenever(loginRepository.login(request))
                .thenReturn(LoginResponse("specific"))

            //Act

            /* Segundo a documentação oficial do Android: Se o teste precisar conferir vários
            valores, chamar toList() vai fazer com que o fluxo aguarde a fonte emitir todos os
            valores e, em seguida, vai retornar esses valores como uma lista */

            //Sendo assim, o método do repositório mockado só vai ser chamado no teste com o uso
            //do método toList()

            loginUseCase(LoginUseCase.LoginParams(request)).toList()

            //Assert
            verify(loginRepository, times(1)).login(request)
        }

    @Test
    fun `check useCase returning a success status`() =
        runTest {

            //Arrange
            whenever(loginRepository.login(any()))
                .thenReturn(LoginResponse("token"))

            //Act
            val result = loginUseCase(LoginUseCase.LoginParams(LoginRequest("user", "password")))
                .toList()

            //Assert
            assert(result[1] is ResultStatus.Success)

        }

    @Test
    fun `check useCase returning a success status even with empty token`() =
        runTest {

            //Arrange
            whenever(loginRepository.login(any()))
                .thenReturn(LoginResponse(""))

            //Act
            val result = loginUseCase(LoginUseCase.LoginParams(LoginRequest("user", "password")))
                .toList()

            //Assert
            assert(result[1] is ResultStatus.Success)

        }

    @Test
    fun `check if useCase really returns loading, and later, a second status`() =
        runTest {
            //Arrange
            whenever(loginRepository.login(any()))
                .thenReturn(LoginResponse(""))

            //Act
            val result = loginUseCase(LoginUseCase.LoginParams(LoginRequest("user", "password")))
                .toList()

            //Assert
            assert(result[0] is ResultStatus.Loading)
            assert(result[1] is ResultStatus.Success)
        }

    @Test
    fun `check useCase returning error`() =
        runTest {
            //Arrange
            whenever(loginRepository.login(any()))
                .thenThrow(RuntimeException("error"))

            //Act
            val result = loginUseCase(LoginUseCase.LoginParams(LoginRequest("user", "password")))
                .toList()

            //Assert
            assert(result[1] is ResultStatus.Error)
        }

}