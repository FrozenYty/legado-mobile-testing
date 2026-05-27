package io.legado.app.unit

import io.legado.app.constant.BookType
import io.legado.app.data.entities.Book
import io.legado.app.data.entities.BookChapter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * TC-008: Book Entity & DAO Validation (Unit Tests)
 *
 * Priority: High
 * Test the Book entity field defaults, BookType constants, and
 * entity construction logic without any Android dependency.
 *
 * @author Tianyu Yao
 */
class TC008_BookEntityTest {

    // ── Book entity field defaults ──────────────────────────────

    @Test
    fun book_defaultConstructor_shouldSetExpectedDefaults() {
        val book = Book()

        assertEquals("", book.bookUrl)
        assertEquals("", book.name)
        assertEquals("", book.author)
        assertEquals(BookType.localTag, book.origin)
        assertEquals("", book.originName)
        assertEquals("", book.tocUrl)
        assertEquals(BookType.text, book.type)
        assertEquals(0L, book.group)
        assertEquals(0, book.durChapterIndex)
        assertEquals(0, book.durChapterPos)
        assertEquals(0, book.totalChapterNum)
        assertTrue(book.canUpdate)
        assertTrue(book.durChapterTime > 0)
    }

    @Test
    fun book_customConstructor_shouldSetFieldsCorrectly() {
        val book = Book(
            bookUrl = "https://example.com/book/123",
            name = "Test Novel",
            author = "Jane Doe",
            origin = "https://source.example.com",
            originName = "Test Source",
            type = BookType.text,
            durChapterTitle = "Prologue",
            durChapterIndex = 3,
            durChapterPos = 42,
            totalChapterNum = 15,
            canUpdate = false,
            group = 5L,
        )

        assertEquals("https://example.com/book/123", book.bookUrl)
        assertEquals("Test Novel", book.name)
        assertEquals("Jane Doe", book.author)
        assertEquals("https://source.example.com", book.origin)
        assertEquals("Test Source", book.originName)
        assertEquals(BookType.text, book.type)
        assertEquals("Prologue", book.durChapterTitle)
        assertEquals(3, book.durChapterIndex)
        assertEquals(42, book.durChapterPos)
        assertEquals(15, book.totalChapterNum)
        assertFalse(book.canUpdate)
        assertEquals(5L, book.group)
    }

    // ── BookType constants ───────────────────────────────────────

    @Test
    fun bookType_text_shouldEqual8() {
        assertEquals(0b1000, BookType.text)
    }

    @Test
    fun bookType_updateError_shouldEqual16() {
        assertEquals(0b10000, BookType.updateError)
    }

    @Test
    fun bookType_audio_shouldEqual32() {
        assertEquals(0b100000, BookType.audio)
    }

    @Test
    fun bookType_image_shouldEqual64() {
        assertEquals(0b1000000, BookType.image)
    }

    @Test
    fun bookType_local_shouldEqual256() {
        assertEquals(0b100000000, BookType.local)
    }

    @Test
    fun bookType_notShelf_shouldEqual1024() {
        assertEquals(0b100_0000_0000, BookType.notShelf)
    }

    @Test
    fun bookType_localTag_shouldBeLocBook() {
        assertEquals("loc_book", BookType.localTag)
    }

    @Test
    fun bookType_webDavTag_shouldBeWebDavPrefix() {
        assertEquals("webDav::", BookType.webDavTag)
    }

    @Test
    fun bookType_allBookType_shouldCombineTextImageAudioWebFile() {
        val expected = BookType.text.or(BookType.image).or(BookType.audio).or(BookType.webFile)
        assertEquals(expected, BookType.allBookType)
    }

    @Test
    fun bookType_allBookTypeLocal_shouldIncludeLocal() {
        val expected = BookType.text.or(BookType.image).or(BookType.audio)
            .or(BookType.webFile).or(BookType.local)
        assertEquals(expected, BookType.allBookTypeLocal)
    }

    @Test
    fun bookType_textAndAudioAreDistinct() {
        assertTrue(BookType.text != BookType.audio)
        assertTrue(BookType.text.and(BookType.audio) == 0)
    }

    // ── BookChapter entity ───────────────────────────────────────

    @Test
    fun bookChapter_defaultConstructor_shouldSetExpectedDefaults() {
        val chapter = BookChapter()

        assertEquals("", chapter.url)
        assertEquals("", chapter.title)
        assertEquals("", chapter.bookUrl)
        assertEquals(0, chapter.index)
        assertFalse(chapter.isVolume)
        assertFalse(chapter.isVip)
        assertFalse(chapter.isPay)
    }

    @Test
    fun bookChapter_customConstructor_shouldSetFieldsCorrectly() {
        val chapter = BookChapter(
            url = "https://example.com/chapter/1",
            title = "Chapter One",
            bookUrl = "https://example.com/book/1",
            index = 1,
            isVolume = true,
            isVip = false,
            isPay = false,
        )

        assertEquals("https://example.com/chapter/1", chapter.url)
        assertEquals("Chapter One", chapter.title)
        assertEquals("https://example.com/book/1", chapter.bookUrl)
        assertEquals(1, chapter.index)
        assertTrue(chapter.isVolume)
    }
}
