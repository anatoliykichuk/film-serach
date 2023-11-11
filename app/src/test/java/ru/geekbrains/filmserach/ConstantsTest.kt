package ru.geekbrains.filmserach

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.geekbrains.filmserach.data.getAllCountries
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.data.getSelectedFields

class ConstantsTest {

    @Test
    fun constants_CountOfAvailableFields_ResultTrue() {
        val expectedCountOfFields = 168
        val selectFields = getSelectedFields()

        assertEquals(selectFields.length, expectedCountOfFields)
    }

    @Test
    fun constants_CountOfAvailableGenres_ResultTrue() {
        val expectedCountOfGenres = 32
        val allGenres = getAllGenres()

        assertEquals(allGenres.size, expectedCountOfGenres)
    }

    @Test
    fun constants_CountOfAvailableCountries_ResultTrue() {
        val expectedCountOfCountries = 235
        val allCountries = getAllCountries()

        assertEquals(allCountries.size, expectedCountOfCountries)
    }

    @Test
    fun constants_GenresContainsValue_ResultTrue() {
        val expectedGenre = "фантастика"
        assertTrue(getAllGenres().contains(expectedGenre))
    }

    @Test
    fun constants_CountriesContainsValue_ResultFalse() {
        val expectedCountry = "canada"
        assertFalse(getAllCountries().containsValue(expectedCountry))
    }
}