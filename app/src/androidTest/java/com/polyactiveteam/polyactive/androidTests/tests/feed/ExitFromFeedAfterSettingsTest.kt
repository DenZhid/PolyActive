package com.polyactiveteam.polyactive.androidTests.tests.feed

import com.polyactiveteam.polyactive.androidTests.screens.LoginScreen
import com.polyactiveteam.polyactive.androidTests.tests.BaseTest

import androidx.lifecycle.Lifecycle
import org.junit.Assert
import org.junit.Test

class ExitFromFeedAfterSettingsTest : BaseTest(), ExitFromFeedInterface {

    /* Авторизуемся -> Переходим в настройки -> Переходим обратно в новостную ленту ->
     * -> Выходим из приложения по нажатию кнопки назад */
    @Test
    override fun exitFromFeedTest() {
        LoginScreen()
            .goToFeedScreen()
            .goToSettingsScreen()
            .goToFeedScreen()
            .goBackWithBackButton()
        Assert.assertTrue(startingScreenRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }
}