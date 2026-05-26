package io.legado.app.espresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.legado.app.R
import io.legado.app.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * TC-001: App Launch
 *
 * Priority: High
 * Precondition: App installed, first launch
 * Steps:
 *   1. Tap app icon
 *   2. Wait for main screen
 * Expected Result: App launches without crash, bookshelf screen displayed
 *
 * @author Tianyu Yao
 */
@RunWith(AndroidJUnit4::class)
class TC001_AppLaunchTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    /** Step 1 & 2: Launch app, dismiss startup dialogs, verify main screen. */
    @Test
    fun appLaunch_shouldDisplayBookshelfScreen() {
        TestHelper.dismissStartupDialogs()

        // Verify the bottom navigation bar is visible
        onView(withId(R.id.bottom_navigation_view))
            .check(matches(isDisplayed()))

        // Verify the main view pager is visible (hosts bookshelf fragment)
        onView(withId(R.id.view_pager_main))
            .check(matches(isDisplayed()))

        // Verify bookshelf is the default tab — either the RecyclerView or
        // the empty-state message should be displayed on first launch.
        try {
            onView(withId(R.id.rv_bookshelf))
                .check(matches(isDisplayed()))
        } catch (_: Exception) {
            // On a fresh install with no books, the empty-state text is shown
            onView(withId(R.id.tv_empty_msg))
                .check(matches(isDisplayed()))
        }
    }

}
