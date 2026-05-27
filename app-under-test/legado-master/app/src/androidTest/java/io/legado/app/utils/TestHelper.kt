package io.legado.app.utils

import android.graphics.Bitmap
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import io.legado.app.data.appDb
import io.legado.app.data.entities.Book
import io.legado.app.data.entities.BookChapter
import java.io.File

/**
 * Utility helpers for Legado tests.
 * Provides common setup/teardown methods shared across test classes.
 *
 * @author Tianyu Yao
 */
object TestHelper {

    /**
     * Dismiss the first-launch dialogs: privacy consent ("同意") and
     * local password setup ("取消" to skip). Safe to call at any time —
     * does nothing if the dialogs are not present.
     */
    fun dismissStartupDialogs() {
        try {
            onView(withText("同意")).perform(click())
        } catch (_: Exception) { }
        try {
            onView(withText("取消")).perform(click())
        } catch (_: Exception) { }
    }

    /** Unique prefix for test book URLs to avoid collisions with real data. */
    const val TEST_URL_PREFIX = "test://legado-test/"

    /** Clean up all test data inserted by [insertTestBook]. */
    fun cleanupTestData(vararg bookUrls: String) {
        bookUrls.forEach { url ->
            try {
                val book = appDb.bookDao.getBook(url)
                if (book != null) {
                    appDb.bookDao.delete(book)
                }
            } catch (_: Exception) {
                // Already deleted or never existed – safe to ignore
            }
        }
    }

    /**
     * Insert a minimal test book with a few chapters, return the book URL so the caller
     * can clean up afterwards.
     */
    fun insertTestBook(
        name: String = "Test Book",
        author: String = "Test Author",
        chapterCount: Int = 5,
        urlSuffix: String = name.replace(" ", "-").lowercase()
    ): String {
        val bookUrl = "${TEST_URL_PREFIX}${urlSuffix}"

        val book = Book(
            bookUrl = bookUrl,
            name = name,
            author = author,
            origin = "https://example.com", // non-local origin to avoid HandleFileActivity
            originName = "Test Source",
            type = 0, // BookType.text
            durChapterTitle = "Chapter 1",
            durChapterIndex = 0,
            durChapterPos = 0,
            totalChapterNum = chapterCount,
            canUpdate = false,
            group = 0L,
        )
        appDb.bookDao.insert(book)

        // Insert corresponding chapters so reader has content
        for (i in 0 until chapterCount) {
            appDb.bookChapterDao.insert(
                BookChapter(
                    bookUrl = bookUrl,
                    index = i,
                    title = "Chapter ${i + 1}",
                    url = "${bookUrl}/chapter/$i",
                    isVolume = i == 0,
                )
            )
        }

        return bookUrl
    }

    /**
     * Capture a screenshot of the current screen and save it to the device.
     * Screenshots are best-effort test evidence — failures are silently ignored
     * so they never break a test run.
     *
     * Files are saved to the app's external files directory. Pull them with:
     *   adb pull /sdcard/Android/data/io.legado.app.debug/files/screenshots/ ../../../../screenshots/
     */
    fun saveScreenshot(name: String) {
        try {
            val bitmap = InstrumentationRegistry.getInstrumentation()
                .uiAutomation
                .takeScreenshot()
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val dir = File(context.getExternalFilesDir(null), "screenshots")
            dir.mkdirs()
            File(dir, "$name.png").outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            }
        } catch (_: Exception) {
            // Screenshot is best-effort evidence, not a test assertion
        }
    }
}
