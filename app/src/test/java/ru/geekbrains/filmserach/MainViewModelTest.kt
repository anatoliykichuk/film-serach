package ru.geekbrains.filmserach

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import okio.IOException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.main.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var observer: Observer<AppState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainViewModel()
    }

    @Test
    fun getFilmsByGenres_IsSuccess() {
        val liveData = viewModel.getLiveData()
        viewModel.getFilmsByGenres()

        try {
            liveData.observeForever(observer)

            //verify(viewModel, times(1)).getFilmsByGenres()

            Assert.assertNotNull(liveData.value)

//            val genre = "фэнтези"
//            val film = Mockito.mock(Film::class.java)
//            val filmsByGenres = mapOf(genre to listOf<Film>(film))
//
//            verify(observer).onChanged(AppState.SuccessGettingFilmsByGenre(filmsByGenres))
        } finally {
            liveData.removeObserver(observer)
        }
    }
}