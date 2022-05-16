package com.natankayevo.nybestsellerbooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natankayevo.nybestsellerbooks.R
import com.natankayevo.nybestsellerbooks.data.model.Book
import com.natankayevo.nybestsellerbooks.databinding.ActivityBooksBinding

class BooksActivity : AppCompatActivity() {
    private lateinit var booksBinding: ActivityBooksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        booksBinding = ActivityBooksBinding.inflate(layoutInflater)
        val booksView = booksBinding.root
        setContentView(booksView)

        booksBinding.toolbar.title = getString(R.string.books_title)
        setSupportActionBar(booksBinding.toolbar)

        val viewModel: BooksViewModel
        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        setObservers(viewModel)

        viewModel.getBooks()
    }

    private fun setObservers(viewModel: BooksViewModel) {
        viewModel.books.observe(this, Observer {
            it?.let { books ->
                with(booksBinding.recyclerView) {
                    layoutManager = LinearLayoutManager(
                        this@BooksActivity,
                        RecyclerView.VERTICAL,
                        false
                    )

                    setHasFixedSize(true)
                    adapter = BooksAdapter(books)
                }
            }
        })
    }

    fun getBooks(): List<Book> = listOf(
        Book("TitleA", "AuthorA"),
        Book("TitleB", "AuthorB ")
    )
}