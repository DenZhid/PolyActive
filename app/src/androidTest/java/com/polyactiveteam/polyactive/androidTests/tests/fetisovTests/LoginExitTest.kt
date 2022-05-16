package com.polyactiveteam.polyactive.androidTests.tests.fetisovTests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polyactiveteam.polyactive.androidTests.tests.pages.FeedPage
import com.polyactiveteam.polyactive.androidTests.tests.pages.LoginPage
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginExitTest : BaseTest() {

    /**
     * Логинимся->заходим в профиль->выходим из аккаунта->проверяем, что обратно войти без
     * логина нельзя.
     */
    @Test
    fun loginAndExitTest() {
        val loginPage = LoginPage()

        val feedPage: FeedPage = loginPage.login()

        val profilePage = feedPage.goToProfilePage()

        profilePage.exit()

        profilePage.checkIsNotLoaded()
    }

}