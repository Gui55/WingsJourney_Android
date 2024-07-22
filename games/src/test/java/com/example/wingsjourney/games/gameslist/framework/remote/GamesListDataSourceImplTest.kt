package com.example.wingsjourney.games.gameslist.framework.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingutils.MainCoroutineRule
import com.example.testingutils.model.GamesGeneralInfoFactory
import com.example.wingsjourney.games.gameslist.data.datasource.GamesDataSource
import com.example.wingsjourney.games.gameslist.network.GamesRetrofitApi
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
class GamesListDataSourceImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var gamesApi: GamesRetrofitApi

    private lateinit var dataSource: GamesDataSource

    private var gamesGeneralInfoFactory = GamesGeneralInfoFactory()

    private val generalGameInfos = listOf(
        gamesGeneralInfoFactory.create(GamesGeneralInfoFactory.GameProduct.AssassinsCreed),
        gamesGeneralInfoFactory.create(GamesGeneralInfoFactory.GameProduct.XenobladeChronicles)
    )

    @Before
    fun setup(){
        dataSource = RetrofitGamesListDataSource(gamesApi)
    }

    @Test
    fun `check gamesApi doesn't return empty response`() =
        runTest {
            //Arrange
            whenever(gamesApi.getGames(any()))
                .thenReturn(generalGameInfos)

            //Act
            val result = dataSource.fetchGames("token")

            //Assert
            assert(result.isNotEmpty())
        }

    @Test(expected = RuntimeException::class)
    fun `check gamesApi returning error`() =
        runTest {
            //Arrange
            whenever(gamesApi.getGames(any()))
                .thenThrow(RuntimeException("error"))

            //Act
            dataSource.fetchGames("token")
        }

}