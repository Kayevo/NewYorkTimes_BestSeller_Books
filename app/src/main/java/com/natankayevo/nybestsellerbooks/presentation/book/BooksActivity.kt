package com.natankayevo.nybestsellerbooks.presentation.book

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.natankayevo.nybestsellerbooks.R
import com.natankayevo.nybestsellerbooks.data.repository.BooksAPIDataSource
import com.natankayevo.nybestsellerbooks.databinding.ActivityBooksBinding
import com.natankayevo.nybestsellerbooks.databinding.IncludeToolbarBinding
import com.natankayevo.nybestsellerbooks.presentation.bookDetails.BookDetailsActivity

class BooksActivity : AppCompatActivity() {
    private lateinit var booksBinding: ActivityBooksBinding
    private lateinit var includeToolBarView: IncludeToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        booksBinding = ActivityBooksBinding.inflate(layoutInflater)
        includeToolBarView = IncludeToolbarBinding.inflate(layoutInflater)

        val booksView = booksBinding.root
        setContentView(booksView)

        includeToolBarView.toolbar.title = getString(R.string.books_title)
        setSupportActionBar(includeToolBarView.toolbar)

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksAPIDataSource())
            .create(BooksViewModel::class.java)

        setObservers(viewModel)

        viewModel.getBooks()
    }

    private fun setObservers(viewModel: BooksViewModel) {
        viewModel.booksLiveData.observe(this) {
            it?.let { books ->

                booksBinding.recyclerView.visibility = View.VISIBLE
                booksBinding.error.visibility = View.INVISIBLE

                with(booksBinding.recyclerView) {
                    layoutManager = LinearLayoutManager(
                        this@BooksActivity,
                        RecyclerView.VERTICAL,
                        false
                    )

                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.author,
                            book.description
                        )

                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        }

        viewModel.error.observe(this){
            it?.let {errorResId ->
                booksBinding.recyclerView.visibility = View.INVISIBLE
                booksBinding.error.visibility = View.VISIBLE
                booksBinding.error.text = getString(errorResId)
            }
        }
    }
}