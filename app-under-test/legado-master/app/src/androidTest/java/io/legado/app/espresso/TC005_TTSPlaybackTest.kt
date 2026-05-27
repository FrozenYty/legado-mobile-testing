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
 * TC-005: TTS Playback
 *
 * Priority: Medium
 * Precondition: Reading view open, TTS engine installed on device
 * Steps:
 *   1. Tap center of screen (to show the reading menu)
 *   2. Tap TTS/read-aloud button
 *   3. Wait for playback
 * Expected Result: Text is read aloud, playback controls visible
 *
 * NOTE: Full TTS playback verification requires a system TTS engine and
 * a book with readable content. The read_menu contains the TTS button
 * (ll_read_aloud). This test validates the reading view opens correctly.
 *
 * @author Tianyu Yao
 */
@RunWith(AndroidJUnit4::class)
class TC005_TTSPlaybackTest {

    private var testBookUrl: String? = null
    private lateinit var scenario: ActivityScenario<ReadBookActivity>

    @Before
    fun setUp() {
        testBookUrl = TestHelper.insertTestBook(
            name = "TTS Test Book",
            author = "Tianyu Yao",
            chapterCount = 8,
            urlSuffix = "tc005-tts-test"
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

    /**
     * Verify the reading view is displayed — the precondition for TTS.
     * Steps 1-3 (TTS playback) require a system TTS engine and are
     * verified manually.
     */
    @Test
    fun readingView_shouldBeDisplayed_forTTSPlayback() {
        onView(withId(R.id.read_view))
            .check(matches(isDisplayed()))
    }
}
