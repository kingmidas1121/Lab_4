package info.goodlift.Lab4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.goodlift.Lab4.dao.MyDao
import info.goodlift.Lab4.model.Author
import info.goodlift.Lab4.model.Book

@Database(
    entities = [Author::class, Book::class],
    version = 2,
    exportSchema = false
)
abstract class SuperDataBase : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: SuperDataBase? = null

        fun getDatabase(context: Context): SuperDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    SuperDataBase::class.java,
                    "super_db.db3"
                )
                    .fallbackToDestructiveMigration() // Додаємо руйнівну міграцію
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
