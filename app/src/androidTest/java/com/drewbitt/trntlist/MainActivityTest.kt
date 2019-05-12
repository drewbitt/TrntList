package com.drewbitt.trntlist

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        // click first child in main recycleriew list
        val linearLayoutCompat = onView(
            allOf(
                withId(R.id.item_container),
                childAtPosition(
                    allOf(
                        withId(R.id.listRecycler),
                        childAtPosition(
                            withId(R.id.refresh),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayoutCompat.perform(click())

        // click fab
        val floatingActionButton = onView(
            allOf(
                withId(R.id.fab_details),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.scroll),
                        0
                    ),
                    6
                )
            )
        )
        floatingActionButton.perform(scrollTo(), click())

        // edit text - add text to it
        val editText = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.custom),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText("testannounce123"), closeSoftKeyboard())

        // click OK in dialog
        val materialButton = onView(
            allOf(
                withId(android.R.id.button1), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        // check for update in text
        val textView = onView(
            allOf(
                withId(R.id.tv_mtv_content), withText(containsString("testannounce123")),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.ll_mtv_root),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText(containsString("testannounce123"))))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }
}
