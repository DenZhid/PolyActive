package com.polyactiveteam.polyactive.androidTests.tests.fetisovTests

import androidx.test.espresso.*
import com.polyactiveteam.polyactive.androidTests.tests.pages.LoginPage
import org.junit.Test


class NewsPersistentTest : BaseTest() {
    /**
     * Открываем приложение -> логинимся -> заходим в новости -> подсчитываем кол-во новостей
     * -> выбираем первую новость-> выходим из нее -> проверяем, что кол-во новостей осталось тем же.
     */
    @Test
    fun newsPersistentTest() {
        val loginPage = LoginPage()
        val feedPage = loginPage.login()
        val newsList = feedPage.newsList
        val initialCountNews = newsList.countNews()

        if (initialCountNews == 0) {
            throw IllegalStateException("No news in Feed Fragment")
        }
        val news = newsList.getNewsAtIndex(0)

        Espresso.pressBack()
        newsList.checkCurrentNewsCountEqualsTo(initialCountNews)
    }

}
