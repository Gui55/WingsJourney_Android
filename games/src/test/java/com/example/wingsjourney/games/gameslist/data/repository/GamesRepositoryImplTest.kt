package com.example.wingsjourney.games.gameslist.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesGeneralInfoFactory
import com.example.wingsjourney.games.gameslist.data.datasource.GamesDataSource
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
class GamesRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getGamesDataSource: GamesDataSource

    private lateinit var gamesRepository: GamesRepository

    private var gamesGeneralInfoFactory = GamesGeneralInfoFactory()

    private val generalGameInfos = listOf(
        gamesGeneralInfoFactory.create(GamesGeneralInfoFactory.GameProduct.AssassinsCreed),
        gamesGeneralInfoFactory.create(GamesGeneralInfoFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setup(){
        gamesRepository = GamesRepositoryImpl(getGamesDataSource)
    }

    @Test
    fun `check positive games list response`() =
        runTest{

            //Arrange
            whenever(getGamesDataSource.fetchGames(any()))
                .thenReturn(
                    generalGameInfos
                )

            //Act
            val result = gamesRepository.getGames("token")

            //Assert
            assert(result.isNotEmpty())
            assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `check repository method returning exception`() = runTest {

        //Arrange
        whenever(getGamesDataSource.fetchGames(any()))
            .thenThrow(
                RuntimeException("error")
            )

        //Act
        gamesRepository.getGames("token")

    }

}