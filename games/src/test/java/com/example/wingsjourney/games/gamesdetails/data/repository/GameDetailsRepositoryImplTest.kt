package com.example.wingsjourney.games.gamesdetails.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesFactory
import com.example.wingsjourney.games.gamesdetails.data.datasource.GameDetailsDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameDetailsRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getGamesDetailsDataSource: GameDetailsDataSource

    private lateinit var gameDetailsRepository: GameDetailsRepository

    private val gamesFactory = GamesFactory()

    private val gamesDetailsList = listOf(
        gamesFactory.create(GamesFactory.GameProduct.AssassinsCreed),
        gamesFactory.create(GamesFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setUp() {
        gameDetailsRepository = GameDetailsRepositoryImpl(getGamesDetailsDataSource)
    }

    @Test
    fun `check not null game details response with index 1`() =
        runTest{

            //Arrange
            val token = "token"
            val index: Long = 1
            val correctGame = gamesDetailsList.find { it.id == index }!!
            whenever(getGamesDetailsDataSource.getDetails(token, index))
                .thenReturn(correctGame)

            //Act
            val result = gameDetailsRepository.getGameDetails(token, index)

            //Assert
            assertNotNull(result)
        }

    @Test
    fun `check not null game details response with index 2`() =
        runTest{

            //Arrange
            val token = "token"
            val index: Long = 2
            val correctGame = gamesDetailsList.find { it.id == index }!!
            whenever(getGamesDetailsDataSource.getDetails(token, index))
                .thenReturn(correctGame)

            //Act
            val result = gameDetailsRepository.getGameDetails(token, index)

            //Assert
            assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `check repository method returning exception`() = runTest {

        //Arrange
        whenever(getGamesDetailsDataSource.getDetails(any(), any()))
            .thenThrow(
                RuntimeException("error")
            )

        //Act
        gameDetailsRepository.getGameDetails("token", 1)

    }
}