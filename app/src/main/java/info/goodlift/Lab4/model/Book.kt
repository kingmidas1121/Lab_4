package info.goodlift.Lab4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var bookId: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "genre")
    val genre: String

) : ListItem {
    override fun getItemType(): Int {
        return ListItem.BOOK_TYPE
    }
}
