package info.goodlift.Lab4.model

interface ListItem {
    fun getItemType(): Int

    companion object {
        const val AUTHOR_TYPE = 1
        const val BOOK_TYPE = 2
    }
}
