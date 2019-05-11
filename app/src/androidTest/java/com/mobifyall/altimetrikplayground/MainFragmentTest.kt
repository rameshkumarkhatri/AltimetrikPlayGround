package com.mobifyall.altimetrikplayground

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.mobifyall.altimetricplayground.MainActivity
import com.mobifyall.music.MainFragment
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )


    @Test
    fun selectSongButtonClick(){
        Espresso.onView(ViewMatchers.withId(R.id.tv_search)).check(ViewAssertions.matches(isDisplayed()))
        val fragment =  (activityRule.activity.supportFragmentManager.fragments.get(0)) as MainFragment
        Espresso.onView(ViewMatchers.withId(R.id.tv_search)).perform(click())
//        Espresso.onView(ViewMatchers.withId(R.id.iv_1)).check(ViewAssertions.matches(isDisplayed()))

//        Espresso.onView(AllOf.allOf(with(androidx)))

    }
}