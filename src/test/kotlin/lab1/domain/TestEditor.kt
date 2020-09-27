package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestEditor {

    private lateinit var editor: Editor

    @BeforeEach
    internal fun setupData() {
        editor = Editor()
    }

    @Test
    fun testEditorGetHead() {
        assertNotNull(editor.head, "Голова не найдена.")
    }

    @Test
    fun testEditorGetLeftEye() {
        assertNotNull(editor.head?.leftEye, "Левый глаз не найден.")
    }

    @Test
    fun testEditorGetRightEye() {
        assertNotNull(editor.head?.rightEye, "Правый глаз не найден.")
    }

    @Test
    fun testEditorHasHeadWhenHeadNull() {
        editor.head = null
        assertFalse(editor.hasHead(), "Головы нет, а она есть.")
    }

    @Test
    fun testEditorHasHeadWhenHeadNotNull() {
        editor.head = Head(Eye(false), Eye(false), false)
        assertTrue(editor.hasHead(), "Голова есть, а ее нет.")
    }

    @Test
    fun testEditorHasLeftEyeWhenHeadNull() {
        editor.head = null
        assertFalse(editor.hasLeftEye(), "Левый глаза есть, а головы нет.")
    }

    @Test
    fun testEditorHasLeftEyeWhenHeadNotNull() {
        editor.head = Head(Eye(false), Eye(false), false)
        assertTrue(editor.hasLeftEye(), "Левого глаза нет, а голова есть.")
    }

    @Test
    fun testEditorHasRightEyeWhenHeadNull() {
        editor.head = null
        assertFalse(editor.hasRightEye(), "Правый глаза есть, а головы нет.")
    }

    @Test
    fun testEditorHasRightEyeWhenHeadNotNull() {
        editor.head = Head(Eye(false), Eye(false), false)
        assertTrue(editor.hasRightEye(), "Правого глаза нет, а голова есть.")
    }

    @Test
    fun testCanReadBookWithoutIll() {
        editor.illNow.clear()
        assertTrue(editor.canDiscoverIdeaBook(), "Редактор не может узнать содержание книги, хотя здоров.")
    }

    @Test
    fun testCanReadBookWithBlindness() {
        editor.illNow.clear()
        editor.addIll(Ill.BLINDNESS)
        assertTrue(editor.canDiscoverIdeaBook(), "Редактор не может узнать содержание книги, хотя слепой.")
    }

    @Test
    fun testCanReadBookWithParalysis() {
        editor.illNow.clear()
        editor.addIll(Ill.PARALYSIS)
        assertTrue(editor.canDiscoverIdeaBook(), "Редактор не может узнать содержание книги, хотя парализован.")
    }

    @Test
    fun testCanReadBookWithDeaf() {
        editor.illNow.clear()
        editor.addIll(Ill.DEAF)
        assertTrue(editor.canDiscoverIdeaBook(), "Редактор не может узнать содержание книги, хотя глухой.")
    }

    @Test
    fun testCanReadBookWithBlindnessAndDeaf() {
        editor.illNow.clear()
        editor.addIll(Ill.BLINDNESS)
        editor.addIll(Ill.DEAF)
        assertFalse(editor.canDiscoverIdeaBook(), "Редактор может узнать содержание книги, хотя слепой и глухой.")
    }

    @Test
    fun testCanReadBookWithBlindnessAndParalysis() {
        editor.illNow.clear()
        editor.addIll(Ill.BLINDNESS)
        editor.addIll(Ill.PARALYSIS)
        assertTrue(editor.canDiscoverIdeaBook(), "Редактор не может узнать содержание книги, хотя слепой и парализован.")
    }

    @Test
    fun testCanReadBookWithParalysisAndDeaf() {
        editor.illNow.clear()
        editor.addIll(Ill.PARALYSIS)
        editor.addIll(Ill.DEAF)
        assertTrue(editor.canDiscoverIdeaBook(), "Редактор не может узнать содержание книги, хотя парализован и глухой.")
    }

    @Test
    fun testCanReadBookWithBlindnessAndParalysisAndDeaf() {
        editor.illNow.clear()
        editor.addIll(Ill.BLINDNESS)
        editor.addIll(Ill.PARALYSIS)
        editor.addIll(Ill.DEAF)
        assertFalse(editor.canDiscoverIdeaBook(), "Редактор может узнать содержание книги, хотя глухой, слепой и парализован.")
    }

    @Test
    fun testGiveOpinionAboutBookWhenRead() {
        val book = Book("name", "author", Actor.DOCTOR)
        editor.illNow.clear()
        editor.library.openLibrary()
        editor.library.addBook(book)
        editor.readBook(book)
        assertTrue(editor.giveOpinionAboutBook(book, Opinion.ENTERTAINING), "Неудается дать отзыв книге, хотя она и прочитана.")
    }

    @Test
    fun testGiveOpinionAboutBookWhenNotRead() {
        val book = Book("name", "author", Actor.DOCTOR)
        assertFalse(editor.giveOpinionAboutBook(book, Opinion.ENTERTAINING), "Удается дать отзыв книге, хотя она и не прочитана.")
    }

    @Test
    fun testReadBookWhenNotIllAndHasBookInLibrary() {
        val book = Book("name", "author", Actor.DOCTOR)
        editor.illNow.clear()
        editor.library.openLibrary()
        editor.library.addBook(book)
        assertTrue(editor.readBook(book), "Неудается прочесть книгу, когда она есть в библиотеке, хотя редактор и не болен.")
    }

    @Test
    fun testReadBookWhenIllAndHasBookInLibrary() {
        val book = Book("name", "author", Actor.DOCTOR)
        editor.illNow.clear()
        editor.addIll(Ill.BLINDNESS)
        editor.addIll(Ill.PARALYSIS)
        editor.addIll(Ill.DEAF)
        editor.library.openLibrary()
        editor.library.addBook(book)
        assertFalse(editor.readBook(book), "Удается прочесть книгу, когда она есть в библиотеке, хотя редактор болен.")
    }

    @Test
    fun testReadBookWhenNotIllAndHasNotBookInLibrary() {
        val book = Book("name", "author", Actor.DOCTOR)
        editor.illNow.clear()
        assertFalse(editor.readBook(book), "Удается прочесть книгу, когда ее нет в библиотеке, хотя редактор и не болен.")
    }

    @Test
    fun testReadBookWhenIllAndHasNotBookInLibrary() {
        val book = Book("name", "author", Actor.DOCTOR)
        editor.illNow.clear()
        editor.addIll(Ill.BLINDNESS)
        editor.addIll(Ill.PARALYSIS)
        editor.addIll(Ill.DEAF)
        assertFalse(editor.readBook(book), "Удается прочесть книгу, когда ее нет в библиотеке, хотя редактор болен.")
    }

}