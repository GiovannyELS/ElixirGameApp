package com.example.elixirgameapp

import com.example.elixirgameapp.data.response.VideoGameResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class VideoGameResponseTest {

    @Test
    fun TestearCreacionDeEntidad (){

        val videoGame = VideoGameResponse(
        id= 1,
            name = "Juegos",
            released = "2022-01-01",
            backgroundImage = "https://www.google.com",
            rating = 4.5,
            metacritic = 90
        )
        assertEquals(1, videoGame.id)
        assertEquals("Juegos", videoGame.name)
        assertEquals("2022-01-01", videoGame.released)
        assertEquals("https://www.google.com", videoGame.backgroundImage)
        assertEquals(4.5, videoGame.rating)
        assertEquals(90, videoGame.metacritic)
    }
}