package ru.geekbrains.filmserach

import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import okio.IOException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.main.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var observer: Observer<AppState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainViewModel()
        viewModel.getLiveData().observeForever(observer)
    }

    @Test
    @Throws(IOException::class)
    fun getFilmsByGenres_IsSuccess() {
        verify(viewModel, times(1)).getFilmsByGenres()

        val genre = "фэнтези"
        val film = Mockito.mock(Film::class.java)
        val filmsByGenres = mapOf(genre to listOf<Film>(film))

        verify(observer).onChanged(AppState.SuccessGettingFilmsByGenre(filmsByGenres))
    }
}