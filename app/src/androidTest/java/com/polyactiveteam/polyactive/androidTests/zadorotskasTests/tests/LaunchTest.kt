package com.polyactiveteam.polyactive.androidTests.zadorotskasTests.tests

import android.view.View
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.polyactiveteam.polyactive.MainActivity
import com.polyactiveteam.polyactive.androidTests.zadorotskasTests.screens.LoginScreen
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

class LaunchTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Логинимся -> попадаем на ленту новостей -> проверяем, что в bottom navigation выбраны новости
    // (Сейчас там баг и выбран профиль)
    @Test
    fun checkBottomNavigationAfterLogin() {
        val feedScreen = LoginScreen().login()
        feedScreen.getButtonFeed().check(matches(withBottomNavItemCheckedStatus(true)))
        feedScreen.getButtonProfile().check(matches(withBottomNavItemCheckedStatus(false)))
    }

    companion object {
        fun withBottomNavItemCheckedStatus(isChecked: Boolean): Matcher<View?> {
            return object :
                BoundedMatcher<View?, BottomNavigationItemView>(BottomNavigationItemView::class.java) {
                var triedMatching = false
                override fun describeTo(description: Description) {
                    if (triedMatching) {
                        description.appendText("with BottomNavigationItem check status: $isChecked")
                        description.appendText("But was: " + (!isChecked).toString())
                    }
                }

                override fun matchesSafely(item: BottomNavigationItemView): Boolean {
                    triedMatching = true
                    return item.itemData!!.isChecked == isChecked
                }
            }
        }
    }
}