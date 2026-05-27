package io.legado.app.espresso

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.legado.app.R
import io.legado.app.utils.TestHelper
import io.legado.app.ui.book.read.ReadBookActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * TC-003: Open Book and Read
 *
 * Priority: High
 * Precondition: At least one book imported
 * Steps:
 *   1. Tap book cover on bookshelf
 *   2. Swipe left to turn page
 *   3. Swipe right to go back
 * Expected Result: Reading view opens, page turns smoothly
 *
 * NOTE: Book content loading requires a real book file on the device.
 * This test validates that the reading activity opens and the read_view
 * is displayed. Full page-turn testing requires a real book with content.
 *
 * @author Tianyu Yao
 */
@RunWith(AndroidJUnit4::class)
class TC003_OpenBookReadTest {

    private var testBookUrl: String? = null
    private lateinit var scenario: ActivityScenario<ReadBookActivity>

    @Before
    fun setUp() {
        testBookUrl = TestHelper.insertTestBook(
            name = "Test Book for Reading",
            author = "Tianyu Yao",
            chapterCount = 10,
            urlSuffix = "tc003-test-book"
        )

        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = Intent(context, ReadBookActivity::class.java).apply {
            putExtra("bookUrl", testBookUrl)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        scenario.close()
        testBookUrl?.let { TestHelper.cleanupTestData(it) }
    }

    /** Step 1: Verify the reading view opens — read_view is displayed. */
    @Test
    fun openBook_shouldDisplayReadingView() {
        onView(withId(R.id.read_view))
            .check(matches(isDisplayed()))

        TestHelper.saveScreenshot("tc003-reading-view")
    }
}
