package com.bignerdranch.android.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CrimeDetailFragmentTest {

    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp() {
        scenario = FragmentScenario.Companion.launchInContainer(CrimeDetailFragment::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun crimeSolvedCheckboxCheckedTest() {
        onView(withId(R.id.crime_solved)).apply {
            perform(click())
            check(matches(isChecked()))
        }
        scenario.onFragment { fragment ->
            assertEquals(fragment.crime.isSolved, true)
        }
    }

    @Test
    fun crimeSolvedCheckboxUncheckedTest() {
        onView(withId(R.id.crime_solved)).apply {
            perform(click())
            perform(click())
            check(matches(isNotChecked()))
        }
        scenario.onFragment { fragment ->
            assertEquals(fragment.crime.isSolved, false)
        }
    }

    @Test
    fun crimeTitleEditTextChangedTest() {
        val typedText = "Title"
        onView(withId(R.id.crime_title)).apply {
            perform(typeText(typedText))
            check(matches(withText(typedText)))
        }
        scenario.onFragment { fragment ->
            assertEquals(fragment.crime.title, typedText)
        }

        onView(withId(R.id.crime_title)).apply {
            perform(clearText())
            check(matches(withText("")))
        }
        scenario.onFragment { fragment ->
            assertEquals(fragment.crime.title, "")
        }
    }
}