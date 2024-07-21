package com.example.wingsjourney.login.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingutils.MainCoroutineRule
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class LoginRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var loginDataSource: LoginDataSource

    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup(){
        loginRepository = LoginRepositoryImpl(loginDataSource)
    }

    @Test
    fun `check positive login response doesn't return empty token or null`() =
        runTest{

            //Arrange
            whenever(loginDataSource.login(any()))
                .thenReturn(
                    LoginResponse("token")
                )

            //Act
            val result = loginRepository.login(LoginRequest("user", "password"))

            //Assert
            assert(result.token.isNotEmpty())
            assertNotNull(result)
        }

    @Test
    fun `check empty token login response`() = runTest {

        //Arrange
        whenever(loginRepository.login(any()))
            .thenReturn(
                LoginResponse("")
            )

        //Act
        val result = loginRepository.login(LoginRequest("user", "password"))

        //Assert
        assert(result.token.isEmpty())

    }

    @Test(expected = RuntimeException::class)
    fun `check repository method returning exception`() = runTest {

        //Arrange
        whenever(loginRepository.login(any()))
            .thenThrow(
                RuntimeException("error")
            )

        //Act
        loginRepository.login(LoginRequest("user", "password"))

    }

}