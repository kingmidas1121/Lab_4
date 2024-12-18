package info.goodlift.Lab4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    var authorId: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "biography")
    val biography: String

) : ListItem {
    override fun getItemType(): Int {
        return ListItem.AUTHOR_TYPE
    }
}
