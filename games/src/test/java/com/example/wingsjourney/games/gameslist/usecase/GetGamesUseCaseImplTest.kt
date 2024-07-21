package com.example.wingsjourney.games.gameslist.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.usecase.base.ResultStatus
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesGeneralInfoFactory
import com.example.wingsjourney.games.gameslist.data.repository.GamesRepository
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
class GetGamesUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var gamesRepository: GamesRepository

    private lateinit var getGamesUseCase: GetGamesUseCase

    private var gamesGeneralInfoFactory = GamesGeneralInfoFactory()

    private val generalGameInfos = listOf(
        gamesGeneralInfoFactory.create(GamesGeneralInfoFactory.GameProduct.AssassinsCreed),
        gamesGeneralInfoFactory.create(GamesGeneralInfoFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setup(){
        getGamesUseCase = GetGamesUseCaseImpl(gamesRepository)
    }

    @Test
    fun `check if useCase really starts with loading status`() =
        runTest{
            //Arrange
            whenever(gamesRepository.getGames(any()))
                .thenReturn(generalGameInfos)

            //Act
            val result = getGamesUseCase(GetGamesUseCase.GetGamesParams("token"))
                .toList()

            //Assert
            assert(result[0] is ResultStatus.Loading)
        }

    @Test
    fun `check if repository is really being called`() =
        runTest {

            //Arrange
            val tk = "token"
            whenever(gamesRepository.getGames(any()))
                .thenReturn(generalGameInfos)

            //Act
            getGamesUseCase(GetGamesUseCase.GetGamesParams(tk)).toList()

            //Assert
            verify(gamesRepository, times(1)).getGames(tk)

        }

    @Test
    fun `check useCase returning a success status`() =
        runTest {

            //Arrange
            val tk = "token"
            whenever(gamesRepository.getGames(any()))
                .thenReturn(generalGameInfos)

            //Act
            val result = getGamesUseCase(GetGamesUseCase.GetGamesParams(tk)).toList()

            //Assert
            assert(result[1] is ResultStatus.Success)

        }


    @Test
    fun `check if useCase really returns loading, and later, a second status`() =
        runTest {
            //Arrange
            val tk = "token"
            whenever(gamesRepository.getGames(any()))
                .thenReturn(generalGameInfos)

            //Act
            val result = getGamesUseCase(GetGamesUseCase.GetGamesParams(tk)).toList()

            //Assert
            assert(result[0] is ResultStatus.Loading)
            assert(result[1] is ResultStatus.Success)
        }

    @Test
    fun `check useCase returning error`() =
        runTest {
            //Arrange
            val tk = "token"
            whenever(gamesRepository.getGames(any()))
                .thenThrow(RuntimeException("error"))

            //Act
            val result = getGamesUseCase(GetGamesUseCase.GetGamesParams(tk)).toList()

            //Assert
            assert(result[1] is ResultStatus.Error)
        }

}