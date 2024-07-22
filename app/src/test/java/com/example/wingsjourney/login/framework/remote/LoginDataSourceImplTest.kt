package com.example.wingsjourney.login.framework.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingutils.MainCoroutineRule
import com.example.wingsjourney.login.data.repository.LoginDataSource
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.example.wingsjourney.login.network.LoginRetrofitApi
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginDataSourceImplTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var loginApi: LoginRetrofitApi

    private lateinit var dataSource: LoginDataSource

    @Before
    fun setup(){
        dataSource = RetrofitLoginDataSource(loginApi)
    }

    @Test
    fun `check loginApi returning a successful response with empty token`() =
        runTest {
            //Arrange
            whenever(loginApi.doAuthentication(any()))
                .thenReturn(LoginResponse("token"))

            //Act
            val result = dataSource.login(LoginRequest("user", "password"))

            //Assert
            assert(result.token.isNotEmpty())
        }

    @Test
    fun `check loginApi returning a successful response with no token`() =
        runTest {
            //Arrange
            whenever(loginApi.doAuthentication(any()))
                .thenReturn(LoginResponse(""))

            //Act
            val result = dataSource.login(LoginRequest("user", "password"))

            //Assert
            assert(result.token.isEmpty())
        }

    @Test(expected = RuntimeException::class)
    fun `check loginApi returning error`() =
        runTest {
            //Arrange
            whenever(loginApi.doAuthentication(any()))
                .thenThrow(RuntimeException("error"))

            //Act
            dataSource.login(LoginRequest("user", "password"))
        }

}