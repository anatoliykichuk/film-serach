package ru.geekbrains.filmserach

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.main.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var observer: Observer<AppState>

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun getFilmsByGenres_IsSuccess() {
        testCoroutineRule.runBlockingTest {
            val liveData = viewModel.getLiveData()

            try {
                liveData.observeForever(observer)

                verify(viewModel, times(1)).getFilmsByGenres()

                val genre = "фэнтези"
                val film = Mockito.mock(Film::class.java)
                val filmsByGenres = mapOf(genre to listOf<Film>(film))

                verify(observer).onChanged(AppState.SuccessGettingFilmsByGenre(filmsByGenres))

            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
}