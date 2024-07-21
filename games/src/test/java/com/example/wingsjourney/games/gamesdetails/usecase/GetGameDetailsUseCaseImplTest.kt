package com.example.wingsjourney.games.gamesdetails.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.usecase.base.ResultStatus
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesFactory
import com.example.wingsjourney.games.gamesdetails.data.repository.GameDetailsRepository
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
class GetGameDetailsUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var gameDetailsRepository: GameDetailsRepository

    private lateinit var getGameDetailsUseCase: GetGameDetailsUseCase

    private val gamesFactory = GamesFactory()

    private val gamesList = listOf(
        gamesFactory.create(GamesFactory.GameProduct.AssassinsCreed),
        gamesFactory.create(GamesFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setup(){
        getGameDetailsUseCase = GetGameDetailsUseCaseImpl(gameDetailsRepository)
    }

    @Test
    fun `check if useCase really starts with loading status`() =
        runTest{
            //Arrange
            val index: Long = 1
            val correctGame = gamesList.find { it.id == index }!!
            whenever(gameDetailsRepository.getGameDetails(any(), any()))
                .thenReturn(correctGame)

            //Act
            val result = getGameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams("token", index))
                .toList()

            //Assert
            assert(result[0] is ResultStatus.Loading)

        }

    @Test
    fun `check if repository is really being called`() =
        runTest {

            //Arrange
            val index: Long = 1
            val correctGame = gamesList.find { it.id == index }!!
            whenever(gameDetailsRepository.getGameDetails(any(), any()))
                .thenReturn(correctGame)

            //Act
            getGameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams("token", index))
                .toList()

            //Assert
            verify(gameDetailsRepository, times(1)).getGameDetails("token", index)

        }

    @Test
    fun `check useCase returning a correct response with index 1`() =
        runTest {

            //Arrange
            val index: Long = 1
            val correctGame = gamesList.find { it.id == index }!!
            whenever(gameDetailsRepository.getGameDetails(any(), any()))
                .thenReturn(correctGame)

            //Act
            val result = getGameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams("token", index))
                .toList()

            //Assert
            assert(result[1] is ResultStatus.Success)

        }

    @Test
    fun `check useCase returning a correct response with index 2`() =
        runTest {

            //Arrange
            val index: Long = 2
            val correctGame = gamesList.find { it.id == index }!!
            whenever(gameDetailsRepository.getGameDetails(any(), any()))
                .thenReturn(correctGame)

            //Act
            val result = getGameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams("token", index))
                .toList()

            //Assert
            assert(result[1] is ResultStatus.Success)

        }


    @Test
    fun `check if useCase really returns loading, and later, a second status`() =
        runTest {
            //Arrange
            val index: Long = 1
            val correctGame = gamesList.find { it.id == index }!!
            whenever(gameDetailsRepository.getGameDetails(any(), any()))
                .thenReturn(correctGame)

            //Act
            val result = getGameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams("token", index))
                .toList()

            //Assert
            assert(result[0] is ResultStatus.Loading)
            assert(result[1] is ResultStatus.Success)
        }

    @Test
    fun `check useCase returning error`() =
        runTest {
            //Arrange
            val index: Long = 1
            whenever(gameDetailsRepository.getGameDetails(any(), any()))
                .thenThrow(RuntimeException("error"))

            //Act
            val result = getGameDetailsUseCase(GetGameDetailsUseCase.GetGameDetailsParams("token", index))
                .toList()

            //Assert
            assert(result[1] is ResultStatus.Error)
        }

}