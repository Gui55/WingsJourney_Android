package com.example.wingsjourney.games.gameslist.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.usecase.base.ResultStatus
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesGeneralInfoFactory
import com.example.wingsjourney.games.gameslist.usecase.GetGamesUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GamesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: GamesViewModel

    @Mock
    private lateinit var getGamesUseCase: GetGamesUseCase

    private val gamesInfoFactory = GamesGeneralInfoFactory()

    private var gamesInfoList = listOf(
        gamesInfoFactory.create(GamesGeneralInfoFactory.GameProduct.AssassinsCreed),
        gamesInfoFactory.create(GamesGeneralInfoFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setup(){
        viewModel = GamesViewModel(getGamesUseCase)
    }

    @Test
    fun `viewModel method should populate games list liveData`() =
        runTest{

            //Arrange
            whenever(getGamesUseCase(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(gamesInfoList)
                    )
                )

            //Act
            viewModel.lookForGames("")

            //Assert
            val result = viewModel.gamesResult.value
            assert(result is ResultStatus.Success)

        }

    @Test
    fun `viewModel method should return loading status`() =
        runTest {
            //Arrange
            whenever(getGamesUseCase(any()))
                .thenReturn(
                    flowOf(ResultStatus.Loading)
                )

            //Act
            viewModel.lookForGames("")

            //Assert
            val result = viewModel.gamesResult.value
            assertTrue(result is ResultStatus.Loading)
        }

    @Test
    fun `viewModel method should return error status`() =
        runTest{
            //Arrange
            whenever(getGamesUseCase(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Error(Exception())
                    )
                )

            //Act
            viewModel.lookForGames("")

            //Assert
            val result = viewModel.gamesResult.value
            assert(result is ResultStatus.Error)

        }

}