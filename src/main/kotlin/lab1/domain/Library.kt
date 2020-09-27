package lab1.domain

/*
все книги уникальны нет повторяющихся названий
* */
class Library {

    // взятые книги
    private val listBooksTaken: MutableSet<Book> = mutableSetOf()
    // все книги
    private val listBooks: MutableSet<Book> = mutableSetOf()
    private var isOpen: Boolean = false

    fun openLibrary() {
        isOpen = true
    }

    fun closeLibrary() {
        isOpen = false
    }

    fun addBook(newBook: Book) : Boolean {
        if (isOpen) {
            return listBooks.add(newBook)
        }
        return false
    }

    fun destroyBook(book: Book) : Boolean {
        if (isOpen) {
            return listBooks.remove(book)
        }
        return false
    }

    fun takeBooksFromLibrary(takeBook: Book) : Boolean {
        if (isOpen) {
            if (hasConcretBookInLibrary(takeBook)) {
                return listBooksTaken.add(takeBook)
            }
            return false
        }
        return false
    }

    fun returnBooksToLibrary(returnBook: Book) : Boolean {
        if (isOpen) {
            return listBooksTaken.remove(returnBook)
        }
        return false
    }

    // есть ли в наличии
    fun hasConcretBookInLibrary(book: Book) : Boolean {
        val hasBookInLibraryCatalog = listBooks.find { book == it } != null
        val hasTakenBook = listBooksTaken.find { book == it } != null
        return hasBookInLibraryCatalog && !hasTakenBook
    }

    fun hasBookInLibraryByCondition(condition: (book: Book) -> Boolean): Boolean {
        val hasBookInLibraryCatalog = listBooks.find { condition(it) } != null
        val hasTakenBook = listBooksTaken.find { condition(it) } != null
        return hasBookInLibraryCatalog && !hasTakenBook
    }

}