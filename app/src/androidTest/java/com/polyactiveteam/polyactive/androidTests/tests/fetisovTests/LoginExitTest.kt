package com.polyactiveteam.polyactive.androidTests.tests.fetisovTests

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polyactiveteam.polyactive.R
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
        val loginButton = Espresso
            .onView(ViewMatchers.withId(R.id.shadow_button))
        loginButton
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        loginButton.perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(waitFor(bigDelay))

        val profileButton = Espresso.onView(ViewMatchers.withId(R.id.action_profile))
        profileButton.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        profileButton.perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(waitFor(usualDelay))

        val exitButton = Espresso.onView(ViewMatchers.withId(R.id.button_exit))
        exitButton.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        exitButton.perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(waitFor(usualDelay))

        loginButton
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBackUnconditionally()
        profileButton.check(ViewAssertions.doesNotExist())//Если вдруг мы смогли вернуться назад
    }

}