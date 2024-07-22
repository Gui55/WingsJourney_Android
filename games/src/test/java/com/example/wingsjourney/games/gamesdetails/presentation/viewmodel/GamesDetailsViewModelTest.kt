package com.example.wingsjourney.games.gamesdetails.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.usecase.base.ResultStatus
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesFactory
import com.example.wingsjourney.games.gamesdetails.usecase.GetGameDetailsUseCase
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
class GamesDetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: GamesDetailsViewModel

    @Mock
    private lateinit var getGameDetailsUseCase: GetGameDetailsUseCase

    private val gamesFactory = GamesFactory()

    private var gamesList = listOf(
        gamesFactory.create(GamesFactory.GameProduct.AssassinsCreed),
        gamesFactory.create(GamesFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setUp() {
        viewModel = GamesDetailsViewModel(getGameDetailsUseCase)
    }

    @Test
    fun `viewModel should return any game details with success`() =
        runTest {
            //Arrange
            whenever(getGameDetailsUseCase(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(gamesList[0])
                    )
                )

            //Act
            viewModel.getGameDetails("", 0)

            //Assert
            assertTrue(viewModel.gameDetailsResult.value is ResultStatus.Success)
        }

    @Test
    fun `viewModel should return game details of index 1`() =
        runTest {
            //Arrange
            val index: Long = 1
            val correctGame = gamesList.find { it.id == index }!!
            val correctStatus = ResultStatus.Success(correctGame)
            whenever(getGameDetailsUseCase(
                    GetGameDetailsUseCase.GetGameDetailsParams("", index)
                )
            )
                .thenReturn(
                    flowOf(
                        correctStatus
                    )
                )

            //Act
            viewModel.getGameDetails("", index)
            val value = viewModel.gameDetailsResult.value as ResultStatus.Success

            //Assert
            assertTrue(value.data.id == index)
            assertTrue(value.data.name == "Assassins Creed")
        }

    @Test
    fun `viewModel should return game details of index 2`() =
        runTest {
            //Arrange
            val index: Long = 2
            val correctGame = gamesList.find { it.id == index }!!
            val correctStatus = ResultStatus.Success(correctGame)
            whenever(getGameDetailsUseCase(
                    GetGameDetailsUseCase.GetGameDetailsParams("", index)
                )
            )
                .thenReturn(
                    flowOf(
                        correctStatus
                    )
                )

            //Act
            viewModel.getGameDetails("", index)
            val value = viewModel.gameDetailsResult.value as ResultStatus.Success

            //Assert
            assertTrue(value.data.id == index)
            assertTrue(value.data.name == "Xenoblade Chronicles")
        }
}