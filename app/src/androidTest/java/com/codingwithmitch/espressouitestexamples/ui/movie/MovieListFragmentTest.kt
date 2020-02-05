package com.codingwithmitch.espressouitestexamples.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.data.FakeMovieData
import com.codingwithmitch.espressouitestexamples.ui.movie.MoviesListAdapter.MovieViewHolder
import org.junit.Rule
import org.junit.Test

class MovieListFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[4]

    /**
     * RecyclerView comes into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLauch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * Select list item, nav to DetailFragment
     * Correct movie is in view?
     */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    /**
     * Select list item, nav to DetailFragment
     * pressBack
     */
    @Test
    fun test_backNavigation_toMovieListFragment() {
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * Select list item, nav to DetailFragment
     * select directors textView, nav to DirectorsFragment
     */
    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_directiors)).perform(click())

        val directors = MOVIE_IN_TEST.directors
        val verifyDirectorsValue = DirectorsFragment.stringBuilderForDirectors(directors!!)

        onView(withId(R.id.directors_text))
            .check(matches(withText(verifyDirectorsValue)))

    }

    /**
     * Select list item, nav to StarActorsFragment
     * select start actors textView, nav to StarActorsFragment
     */
    @Test
    fun test_navStarActorsFragment_validateActorsList() {

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        val actors = MOVIE_IN_TEST.star_actors
        val verifyActorsValue = StarActorsFragment.stringBuilderForStarActors(actors!!)

        onView(withId(R.id.star_actors_text))
            .check(matches(withText(verifyActorsValue)))

    }
}