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
import org.mockito.Mockito
import ru.geekbrains.filmserach.data.db.FilmDao
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.pages.list.FilmListViewModel

@RunWith(AndroidJUnit4::class)
class FilmListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: FilmListViewModel

    private lateinit var filmDatabase: FilmDatabase
    private lateinit var filmDao: FilmDao

    private lateinit var observer: Observer<AppState>

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        filmDatabase = Room.inMemoryDatabaseBuilder(
            context, FilmDatabase::class.java
        ).build()

        filmDao = filmDatabase.getFilmDao()
        viewModel = FilmListViewModel(filmDatabase)
    }

    @After
    fun close() {
        filmDatabase.close()
    }

    @Test
    fun getFavorites_IsSuccess() {
        testCoroutineRule.runBlockingTest {
            val liveData = viewModel.getLiveData()

            try {
                liveData.observeForever(observer)

                verify(viewModel, times(1)).getFavorites()

                val film = Mockito.mock(Film::class.java)
                val films = listOf<Film>(film)
                verify(observer).onChanged(AppState.SuccessGettingFavoritesFilms(films))

            } finally {
                liveData.removeObserver(observer)
            }

        }
    }
}