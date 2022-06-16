package com.natankayevo.nybestsellerbooks.presentation.bookDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natankayevo.nybestsellerbooks.R
import com.natankayevo.nybestsellerbooks.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var bookDetailsView : ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get values from intent, from another activity
        val title = intent.getStringExtra(EXTRA_TITLE)
        val author = intent.getStringExtra(EXTRA_AUTHOR)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        bookDetailsView = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(bookDetailsView.root)

        bookDetailsView.bookTitle.text = title
        bookDetailsView.bookAuthor.text = author
        bookDetailsView.bookDescription.text = description

    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_AUTHOR = "EXTRA_AUTHOR"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(
            context: Context,
            title: String,
            author: String,
            description: String
        ): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_AUTHOR, author)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}