package com.example.wingsjourney.games.utilinfo

import com.example.testingutils.model.GamesFactory
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class UtilTests {

    private val gamesFactory = GamesFactory()

    private var gamesList = listOf(
        gamesFactory.create(GamesFactory.GameProduct.AssassinsCreed),
        gamesFactory.create(GamesFactory.GameProduct.XenobladeChronicles)
    )

    @Test
    fun `the method find will not return null and will be the first element`() =
        runTest {
            //Arrange
            val index: Long = 1

            //Act
            val result = gamesList.find { it.id == index }

            //Assert
            assertNotNull(result)
            assertEquals(result, gamesList[0])
        }

}