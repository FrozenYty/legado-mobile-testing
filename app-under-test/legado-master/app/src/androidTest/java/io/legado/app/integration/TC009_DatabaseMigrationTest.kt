package io.legado.app.integration

import android.content.ContentValues
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.legado.app.constant.BookType
import io.legado.app.data.AppDatabase
import io.legado.app.data.DatabaseMigrations
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * TC-009: Database Migration Integrity (Room Integration Test)
 *
 * Priority: Medium
 * Verify that Room database migrations from older schema versions
 * execute without errors and that data survives the migration intact.
 *
 * Uses Room's MigrationTestHelper to create a database at an earlier
 * version, insert sample data, then migrate to the latest version
 * and verify the data is still present.
 *
 * @author Tianyu Yao
 */
@RunWith(AndroidJUnit4::class)
class TC009_DatabaseMigrationTest {

    private val testDbName = "tc009-migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName!!,
        FrameworkSQLiteOpenHelperFactory()
    )

    @After
    fun tearDown() {
        InstrumentationRegistry.getInstrumentation()
            .targetContext.deleteDatabase(testDbName)
    }

    /**
     * Create a database at version 50, insert a book, migrate to
     * the latest version, and verify the book still exists.
     */
    @Test
    fun migrateFromVersion50_toLatest_shouldPreserveData() {
        // Step 1: Create the database at version 50
        helper.createDatabase(testDbName, 50).apply {
            execSQL(
                "INSERT INTO books (bookUrl, name, author, origin, originName, type, " +
                    "durChapterTime, lastCheckTime, latestChapterTime) " +
                    "VALUES ('test-book-url-50','Migration Test Book','Test Author'," +
                    "'loc_book','Test',8,${System.currentTimeMillis()}," +
                    "${System.currentTimeMillis()},${System.currentTimeMillis()})"
            )
            close()
        }

        // Step 2: Open with all migrations applied
        val db = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java,
            testDbName
        ).addMigrations(*DatabaseMigrations.migrations)
            .build()

        // Step 3: Verify the database opened and data survived
        val migratedBook = db.bookDao.getBook("test-book-url-50")
        assertNotNull("Book should survive migration from version 50", migratedBook)
        assertEquals("Migration Test Book", migratedBook!!.name)
        assertEquals("Test Author", migratedBook.author)

        db.close()
    }

    /**
     * Verify the latest database version opens cleanly
     * (no migration needed for fresh install).
     */
    @Test
    fun freshDatabase_shouldOpenAtLatestVersion() {
        val db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        ).addMigrations(*DatabaseMigrations.migrations)
            .build()

        // Simple read/write test on fresh DB
        db.bookDao.insert(
            io.legado.app.data.entities.Book().apply {
                bookUrl = "fresh-test-url"
                name = "Fresh Book"
                author = "Tester"
                origin = "https://example.com"
                originName = "Test"
                type = BookType.text
            }
        )

        val found = db.bookDao.getBook("fresh-test-url")
        assertNotNull(found)
        assertEquals("Fresh Book", found!!.name)

        db.close()
    }
}
