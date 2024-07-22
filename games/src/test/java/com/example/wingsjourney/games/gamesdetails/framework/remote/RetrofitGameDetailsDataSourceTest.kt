package com.example.wingsjourney.games.gamesdetails.framework.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesFactory
import com.example.wingsjourney.games.gamesdetails.data.datasource.GameDetailsDataSource
import com.example.wingsjourney.games.gamesdetails.network.GameDetailsRetrofitApi
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
class RetrofitGameDetailsDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var gamesDetailsRetrofitApi: GameDetailsRetrofitApi

    private lateinit var getGamesDetailsDataSource: GameDetailsDataSource

    private val gamesFactory = GamesFactory()

    private val gamesDetailsList = listOf(
        gamesFactory.create(GamesFactory.GameProduct.AssassinsCreed),
        gamesFactory.create(GamesFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setUp() {
        getGamesDetailsDataSource = RetrofitGameDetailsDataSource(gamesDetailsRetrofitApi)
    }

    @Test
    fun `check gamesApi doesn't return empty response with index 1`() =
        runTest {
            //Arrange
            val token = "token"
            val index: Long = 1
            val correctGame = gamesDetailsList.find { it.id == index }!!
            whenever(getGamesDetailsDataSource.getDetails(token, index))
                .thenReturn(correctGame)

            //Act
            val result = getGamesDetailsDataSource.getDetails(token, index)

            //Assert
            assertNotNull(result)
        }

    @Test
    fun `check gamesApi doesn't return empty response with index 2`() =
        runTest {
            //Arrange
            val token = "token"
            val index: Long = 2
            val correctGame = gamesDetailsList.find { it.id == index }!!
            whenever(getGamesDetailsDataSource.getDetails(token, index))
                .thenReturn(correctGame)

            //Act
            val result = getGamesDetailsDataSource.getDetails(token, index)

            //Assert
            assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `check gamesApi returning error`() =
        runTest {
            //Arrange
            whenever(gamesDetailsRetrofitApi.getGameDetails(any(), any()))
                .thenThrow(RuntimeException("error"))

            //Act
            getGamesDetailsDataSource.getDetails("token", 1)
        }
}