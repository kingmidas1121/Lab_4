package info.goodlift.Lab4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import info.goodlift.Lab4.database.SuperDataBase
import info.goodlift.Lab4.model.Author
import info.goodlift.Lab4.model.Book
import info.goodlift.Lab4.model.ListItem
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val superDataBase by lazy { SuperDataBase.getDatabase(application) }
    private val dao by lazy { superDataBase.myDao() }

    private val _itemList = MutableLiveData<ArrayList<ListItem>>()
    val itemList: LiveData<ArrayList<ListItem>> get() = _itemList

    init {
        createData()
    }

    private fun getData() {
        viewModelScope.launch {
            val dataList = ArrayList<ListItem>()
            dataList.addAll(dao.getAllAuthors())
            dataList.addAll(dao.getAllBooks())
            _itemList.value = dataList
        }
    }

    fun clearData() {
        viewModelScope.launch {
            dao.deleteAllAuthors()
            dao.deleteAllBooks()
            getData()
        }
    }

    fun createData() {
        viewModelScope.launch {
            dao.deleteAllAuthors()
            dao.deleteAllBooks()

            val author = Author(name = "J.K. Rowling", biography = "Author of Harry Potter")
            val authorId = dao.insertAuthor(author)


            val book = Book(title = "Harry Potter", genre = "Fantasy")
            dao.insertBook(book)
            val book1 = Book(title = "The Great Gatsby", genre = "Classic Fiction")
            dao.insertBook(book1)
            val book2 = Book(title = "To Kill a Mockingbird", genre = "Historical Fiction")
            dao.insertBook(book2)

            getData()
        }
    }
}
