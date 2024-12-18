package info.goodlift.Lab4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import info.goodlift.Lab4.MyViewModel
import info.goodlift.Lab4.databinding.AuthorListItemBinding
import info.goodlift.Lab4.databinding.BookListItemBinding
import info.goodlift.Lab4.model.Author
import info.goodlift.Lab4.model.Book
import info.goodlift.Lab4.model.ListItem

class MyAdapter(
    private val itemList: LiveData<ArrayList<ListItem>>,
    private val vm: MyViewModel
) : RecyclerView.Adapter<MyAdapter.DataViewHolder>() {

    abstract class DataViewHolder(itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun bind(item: ListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return when (viewType) {
            ListItem.AUTHOR_TYPE -> AuthorHolder(
                AuthorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                vm
            )

            ListItem.BOOK_TYPE -> BookHolder(
                BookListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                vm
            )

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList.value!![position].getItemType()
    }

    override fun getItemCount(): Int {
        return itemList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList.value!![position])
    }

    class AuthorHolder(
        private val itemBinding: AuthorListItemBinding,
        private val vm: MyViewModel
    ) : DataViewHolder(itemBinding) {
        override fun bind(item: ListItem) {
            item as Author
            itemBinding.authorNameTextView.text = item.name
            itemBinding.authorBioTextView.text = item.biography
        }
    }

    class BookHolder(
        private val itemBinding: BookListItemBinding,
        private val vm: MyViewModel
    ) : DataViewHolder(itemBinding) {
        override fun bind(item: ListItem) {
            item as Book
            itemBinding.bookTitleTextView.text = item.title
            itemBinding.bookGenreTextView.text = item.genre
        }
    }
}
