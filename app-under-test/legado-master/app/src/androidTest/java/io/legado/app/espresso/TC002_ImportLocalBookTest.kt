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
 * TC-002: Import Local TXT Book
 *
 * Priority: High
 * Precondition: A .txt file stored on device
 * Steps:
 *   1. Open app
 *   2. Tap "+" or import button
 *   3. Select local file
 *   4. Choose the .txt file
 * Expected Result: Book appears on bookshelf with correct title
 *
 * NOTE: Steps 2-4 trigger Android's Storage Access Framework (SAF)
 * file picker, which cannot be automated by Espresso alone. The "+"
 * (Add local) option is accessible via the bookshelf overflow menu
 * (menu_add_local). This test verifies Step 1 — the main screen is
 * ready for import. File selection requires manual testing or UIAutomator.
 *
 * @author Tianyu Yao
 */
@RunWith(AndroidJUnit4::class)
class TC002_ImportLocalBookTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    /** Step 1: Verify the main bookshelf screen is displayed and ready. */
    @Test
    fun appLaunch_shouldDisplayMainScreen_forImport() {
        TestHelper.dismissStartupDialogs()

        // Verify the main screen is displayed — the bookshelf is ready
        // for the user to tap the "+" / import button
        onView(withId(R.id.bottom_navigation_view))
            .check(matches(isDisplayed()))

        onView(withId(R.id.view_pager_main))
            .check(matches(isDisplayed()))
    }
}
