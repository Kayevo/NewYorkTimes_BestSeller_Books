package com.natankayevo.nybestsellerbooks.presentation.book

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natankayevo.nybestsellerbooks.data.model.Book
import com.natankayevo.nybestsellerbooks.databinding.ItemBookBinding

class BooksAdapter(
    private val books: List<Book>
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemBookView = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BooksViewHolder(itemBookView)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount(): Int = books.size

    inner class BooksViewHolder(private val itemBookBinding: ItemBookBinding) :
        RecyclerView.ViewHolder(itemBookBinding.root) {

        private val viewTitle: TextView = itemBookBinding.bookTitle
        private val viewAuthor: TextView = itemBookBinding.bookAuthor

        fun bindView(book: Book) {
            viewTitle.text = book.title
            viewAuthor.text = book.author

            itemBookBinding.root.setOnClickListener {

            }

            /*
            binding.root.setOnClickListener {
                onItemClick?.invoke(subscriber)
            }*/
        }
    }
}