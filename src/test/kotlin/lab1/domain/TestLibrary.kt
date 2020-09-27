package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestLibrary {

    private lateinit var library: Library

    @BeforeEach
    internal fun setupData() {
        library = Library()
    }

    @Test
    fun testAddBookInOpenLibraryWhenLibraryHasNotBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        assertTrue(library.addBook(book), "Книга не была добавлена в открытую библиотеку, когда ее там нет.")
    }

    @Test
    fun testAddBookInCloseLibraryWhenLibraryHasNotBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.closeLibrary()
        assertFalse(library.addBook(book), "Книга была добавлена в закрытую библиотеку, когда ее там нет.")
    }

    @Test
    fun testAddBookInOpenLibraryWhenLibraryHasBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name", "author", Actor.DOCTOR))
        assertFalse(library.addBook(book), "Книга не была добавлена в открытую библиотеку, когда она там есть.")
    }

    @Test
    fun testAddBookInCloseLibraryWhenLibraryHasBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name", "author", Actor.DOCTOR))
        library.closeLibrary()
        assertFalse(library.addBook(book), "Книга была добавлена в закрытую библиотеку, когда она там есть.")
    }

    @Test
    fun testDestroyBookFromOpenLibraryWhenLibraryHasBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name", "author", Actor.DOCTOR))
        assertTrue(library.destroyBook(book), "Книга не была удалена из открытой библиотеки, когда она там была.")
    }

    @Test
    fun testDestroyBookFromCloseLibraryWhenLibraryHasBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name", "author", Actor.DOCTOR))
        library.closeLibrary()
        assertFalse(library.destroyBook(book), "Книга была удалена из закрытой библиотеки, когда она там была.")
    }

    @Test
    fun testDestroyBookFromOpenLibraryWhenLibraryHasNotBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        assertFalse(library.destroyBook(book), "Книга не была удалена из открытой библиотеки, когда ее там не было.")
    }

    @Test
    fun testDestroyBookFromCloseLibraryWhenLibraryHasNotBook() {
        val book = Book("name", "author", Actor.DOCTOR)
        library.openLibrary()
        library.closeLibrary()
        assertFalse(library.destroyBook(book), "Книга была удалена из закрытой библиотеки, когда ее там не было.")
    }

    @Test
    fun testTakeBookFromOpenLibraryWhenLibraryHasBook() {
        val book = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        assertTrue(library.takeBooksFromLibrary(book), "Книгу не удалось взять из открытой библиотеки, хотя она там есть.")
    }

    @Test
    fun testTakeBookFromCloseLibraryWhenLibraryHasBook() {
        val book = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        library.closeLibrary()
        assertFalse(library.takeBooksFromLibrary(book), "Книгу удалось взять из закрытой библиотеки, хотя она там есть.")
    }

    @Test
    fun testTakeBookFromOpenLibraryWhenLibraryHasNotBook() {
        val book = Book("name0", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        assertFalse(library.takeBooksFromLibrary(book), "Книгу не удалось взять из открытой библиотеки, хотя ее там нет.")
    }

    @Test
    fun testTakeBookFromCloseLibraryWhenLibraryHasNotBook() {
        val book = Book("name0", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        library.closeLibrary()
        assertFalse(library.takeBooksFromLibrary(book), "Книгу удалось взять из закрытой библиотеки, хотя ее там нет.")
    }

    @Test
    fun testReturnBookToOpenLibraryWhenLibraryHasBook() {
        val book = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        assertFalse(library.returnBooksToLibrary(book), "Книгу удалось вернуть в открытую библиотеку, когда она там есть.")
    }

    @Test
    fun testReturnBookToCloseLibraryWhenLibraryHasBook() {
        val book = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        library.closeLibrary()
        assertFalse(library.returnBooksToLibrary(book), "Книгу удалось вернуть в закрытую библиотеку, когда она там есть.")
    }

    @Test
    fun testReturnBookToOpenLibraryWhenLibraryHasNotBook() {
        val book0 = Book("name0", "author", Actor.DOCTOR)
        val book1 = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        library.takeBooksFromLibrary(Book("name1", "author", Actor.DOCTOR))
        assertFalse(library.returnBooksToLibrary(book0), "Книгу удалось вернуть в открытую библиотеку, когда ее там нет и не было никогда.")
        assertTrue(library.returnBooksToLibrary(book1), "Книгу не удалось вернуть в открытую библиотеку, когда ее там нет.")
    }

    @Test
    fun testReturnBookToCloseLibraryWhenLibraryHasNotBook() {
        val book = Book("name0", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        library.takeBooksFromLibrary(Book("name1", "author", Actor.DOCTOR))
        library.closeLibrary()
        assertFalse(library.returnBooksToLibrary(book), "Книгу удалось вернуть в закрытую библиотеку, когда ее там нет.")
    }

    @Test
    fun testCanTakeBookFromOpenLibraryWhenLibraryHasBook() {
        val testBook = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        assertTrue(library.hasConcretBookInLibrary(testBook), "Книги нет в библиотеке, хотя она там есть.")
        assertTrue(library.hasBookInLibraryByCondition { book -> testBook.name == book.name }, "Книги нет в библиотеке, хотя ее название там есть. Ведь имена книг уникальны!")
    }

    @Test
    fun testCanTakeBookFromOpenLibraryWhenLibraryHasNotBook() {
        val testBook = Book("name0", "author", Actor.DOCTOR)
        val takenBook = Book("name1", "author", Actor.DOCTOR)
        library.openLibrary()
        library.addBook(Book("name1", "author", Actor.DOCTOR))
        library.addBook(Book("name2", "author", Actor.DOCTOR))
        library.addBook(Book("name3", "author", Actor.DOCTOR))
        library.takeBooksFromLibrary(takenBook)
        assertFalse(library.hasConcretBookInLibrary(testBook), "Книга есть в библиотеке, хотя ее там нет и не было никогда.")
        assertFalse(library.hasConcretBookInLibrary(takenBook), "Книга есть в библиотеке, хотя ее там нет(кто-то взял).")
        assertFalse(library.hasBookInLibraryByCondition { book -> testBook.name == book.name }, "Книга есть в библиотеке, хотя ее там нет и не было никогда.")
        assertFalse(library.hasBookInLibraryByCondition { book -> takenBook.name == book.name }, "Книга есть в библиотеке, хотя ее там нет(кто-то взял).")
    }

}