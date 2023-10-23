package ru.geekbrains.filmserach

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    @Before
    fun setUp() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun activity_isStarted() {
        val mainMenu = uiDevice.findObject(By.res(packageName, "main_menu"))
        Assert.assertNotNull(mainMenu)
    }

    @Test
    fun mainScreen_filmsByGenresAreDisplayed() {
        val listButton = uiDevice.findObject(By.res(packageName, "menu_list"))
        listButton.clickAndWait(Until.newWindow(), TIMEOUT)

        val filmListIsOpened = uiDevice.findObject(
            By.res(packageName, "film_list_fragment")
        )
        Assert.assertNotNull(filmListIsOpened)
    }

    @Test
    fun searchScreen_expectedNumberOfFilmsByGenresDisplayed() {
        mainScreen_filmsByGenresAreDisplayed()

        val listView = uiDevice.findObject(By.res(packageName, "film_list_fragment"))
        val itemsCount = 4
        Assert.assertEquals(listView.childCount, itemsCount)
    }

    @Test
    fun mainScreen_favoriteFilmsAreDisplayed() {
        val listButton = uiDevice.findObject(By.res(packageName, "menu_favorites"))
        listButton.clickAndWait(Until.newWindow(), TIMEOUT)

        val filmListIsOpened = uiDevice.findObject(
            By.res(packageName, "favorites_list")
        )
        Assert.assertNotNull(filmListIsOpened)
    }

    @Test
    fun searchScreen_expectedNumberOfFavoritesFilmsDisplayed() {
        mainScreen_favoriteFilmsAreDisplayed()

        val listView = uiDevice.findObject(By.res(packageName, "favorites_list"))
        val itemsCount = 8
        Assert.assertEquals(listView.childCount, itemsCount)
    }

    @Test
    fun searchScreen_filmsByNameAreSearched() {
        val searchButton = uiDevice.findObject(By.res(packageName, "menu_search"))
        searchButton.clickAndWait(Until.newWindow(), TIMEOUT)

        val filmNameView = uiDevice.findObject(By.res(packageName, "name"))
        val filmName = "007"
        filmNameView.text = filmName

        val findButton = uiDevice.findObject(By.res(packageName, "start_searching"))
        val filmListIsOpened = findButton.clickAndWait(Until.newWindow(), TIMEOUT)
        Assert.assertNotNull(filmListIsOpened)
    }

    @Test
    fun searchScreen_expectedNumberOfFoundFilmsDisplayed() {
        searchScreen_filmsByNameAreSearched()

        val listView = uiDevice.findObject(By.res(packageName, "favorites_list"))
        val itemsCount = 10
        Assert.assertEquals(listView.childCount, itemsCount)
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}