package ru.geekbrains.filmserach

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.geekbrains.filmserach.data.db.FilmDao
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.pages.film.FilmViewModel

@RunWith(AndroidJUnit4::class)
class FilmViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: FilmViewModel

    private lateinit var filmDatabase: FilmDatabase
    private lateinit var filmDao: FilmDao

    @Mock
    private lateinit var observer: Observer<Boolean>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val context = ApplicationProvider.getApplicationContext<Context>()

        filmDatabase = Room.inMemoryDatabaseBuilder(
            context, FilmDatabase::class.java
        ).build()

        filmDao = filmDatabase.getFilmDao()
        viewModel = FilmViewModel(filmDatabase)
    }

    @After
    fun close() {
        filmDatabase.close()
    }

    @Test
    fun isFavorite_IsSuccess() {
        testCoroutineRule.runBlockingTest {
            val liveData = viewModel.getLiveData()

            try {
                liveData.observeForever(observer)

                val film = Mockito.mock(Film::class.java)
                verify(viewModel, times(1)).isFavorite(film)
                verify(observer).onChanged(film.isFavorite)

                verify(viewModel, times(1)).changeFavoritesTag(film)
                verify(observer).onChanged(!film.isFavorite)

            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
}