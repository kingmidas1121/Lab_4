package info.goodlift.Lab4.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.goodlift.Lab4.model.Author
import info.goodlift.Lab4.model.Book

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: Author): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book): Long

    @Query("SELECT * FROM authors WHERE author_id = :id")
    suspend fun getAuthorById(id: Long): Author?

    @Query("SELECT * FROM authors")
    suspend fun getAllAuthors(): List<Author>
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Delete
    suspend fun deleteAuthor(author: Author)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("Delete From authors")
    suspend fun deleteAllAuthors()


    @Query("Delete From books")
    suspend fun deleteAllBooks()
}