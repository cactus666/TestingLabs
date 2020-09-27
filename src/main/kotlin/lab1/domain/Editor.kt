package domain

import java.util.ArrayList

class Editor {

    var head: Head? = null

    val library: Library
//    var lifeStatus: LifeStatus = LifeStatus.ALIVE_WITHOUT_ILL
//    set(value) {
//        field = value
//        when (value) {
//            LifeStatus.DEAD -> head?.isBrainDied = true
//        }
//    }
    val illNow: ArrayList<Ill> = arrayListOf()
    val listBook: ArrayList<Pair<Book, Opinion?>> = arrayListOf()

    init {
        head = Head(
            Eye(false),
            Eye(false),
            false
        )
        library = Library()
    }

    fun hasHead() : Boolean {
        return head != null
    }

    fun hasLeftEye() : Boolean {
        return head?.leftEye != null
    }

    fun hasRightEye() : Boolean {
        return head?.rightEye != null
    }

    fun addIll(ill: Ill) {
        illNow.add(ill)
//        lifeStatus = LifeStatus.ALIVE_WITH_ILL
        if (ill == Ill.BLINDNESS) {
            head?.leftEye?.isBlind = true
            head?.rightEye?.isBlind = true
        }
    }

//    fun recover(ill: Ill) {
//        illNow.remove(ill)
//        if (illNow.isEmpty()) {
//            lifeStatus = LifeStatus.ALIVE_WITHOUT_ILL
//        }
//        if (ill == Ill.BLINDNESS) {
//            head?.leftEye?.isBlind = false
//            head?.rightEye?.isBlind = false
//        }
//    }

    fun readBook(book: Book) : Boolean {
        return if (canDiscoverIdeaBook() && library.hasConcretBookInLibrary(book)) {
            listBook.add(Pair(book, null))
        } else {
            false
        }
    }

    fun canDiscoverIdeaBook() : Boolean {
        val hasBlindness = illNow.find { it == Ill.BLINDNESS } != null
        val hasDeaf = illNow.find { it == Ill.DEAF } != null
        val hasParalysis = illNow.find { it == Ill.PARALYSIS } != null
        return !((hasBlindness && hasDeaf) || (hasBlindness && hasDeaf && hasParalysis))
    }

    fun giveOpinionAboutBook(book: Book, opinion: Opinion) : Boolean {
        val index = listBook.indexOfFirst { it.first == book }
        return if (index != -1) {
            listBook[index] = Pair(book, opinion)
            true
        } else {
            false
        }
    }

}