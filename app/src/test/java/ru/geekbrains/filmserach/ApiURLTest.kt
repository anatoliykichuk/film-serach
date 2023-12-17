package ru.geekbrains.filmserach

import junit.framework.TestCase.assertEquals
import org.junit.Test
import ru.geekbrains.filmserach.data.net.END_POINT
import ru.geekbrains.filmserach.data.net.PATH

class ApiUrlTest {

    @Test
    fun apiUrl_PathIsCorrect_ResultTrue() {
        val expectedPath = "https://api.kinopoisk.dev"
        assertEquals(PATH, expectedPath)
    }

    @Test
    fun apiUrl_EndPointCorrect_ResultTrue() {
        val expectedEndPoint = "v1.4/movie"
        assertEquals(END_POINT, expectedEndPoint)
    }
}