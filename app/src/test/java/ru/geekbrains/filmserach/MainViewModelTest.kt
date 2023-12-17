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
import org.mockito.MockitoAnnotations
import ru.geekbrains.filmserach.data.Storable
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.UserPreferences
import ru.geekbrains.filmserach.ui.base.ResponseData
import ru.geekbrains.filmserach.ui.main.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var observer: Observer<AppState>

    private lateinit var repository: Storable
    private lateinit var userPreferences: UserPreferences

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = MainViewModel(repository, userPreferences)
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

                verify(observer).onChanged(AppState.Success(ResponseData(filmsByGenres = filmsByGenres)))

            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
}