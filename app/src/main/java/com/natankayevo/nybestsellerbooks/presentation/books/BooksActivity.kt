package com.natankayevo.nybestsellerbooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
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
        val view = booksBinding.root
        setContentView(view)

        booksBinding.toolbar.title = getString(R.string.books_title)
        setSupportActionBar(booksBinding.toolbar)

        with(booksBinding.recyclerView) {
            layoutManager = LinearLayoutManager(
                this@BooksActivity,
                RecyclerView.VERTICAL,
                false
            )

            setHasFixedSize(true)
            adapter = BooksAdapter(getBooks())
        }

        // val viewModel = ViewModelProvider(booksBinding.root)
    }

    fun getBooks(): List<Book> = listOf(
        Book("TitleA", "AuthorA"),
        Book("TitleB", "AuthorB ")
    )
}