package ru.geekbrains.filmserach

import android.content.Context
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.geekbrains.filmserach.data.db.FilmDao
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.pages.list.FilmListViewModel

class FilmListViewModelTest {

    private lateinit var viewModel: FilmListViewModel

    private lateinit var filmDatabase: FilmDatabase
    private lateinit var filmDao: FilmDao

    @Mock
    private lateinit var observer: Observer<AppState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val context = ApplicationProvider.getApplicationContext<Context>()

        filmDatabase = Room.inMemoryDatabaseBuilder(
            context, FilmDatabase::class.java
        ).build()

        filmDao = filmDatabase.getFilmDao()
        viewModel = FilmListViewModel(filmDatabase)

        viewModel.getLiveData().observeForever(observer)
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        filmDatabase.close()
    }

    @Test
    @Throws(IOException::class)
    fun getFavorites_Test_Success() {
        verify(viewModel, times(1)).getFavorites()

        val film = Mockito.mock(Film::class.java)
        val films = listOf<Film>(film)
        verify(observer).onChanged(AppState.SuccessGettingFavoritesFilms(films))
    }
}