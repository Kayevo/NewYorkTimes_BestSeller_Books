package com.natankayevo.nybestsellerbooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natankayevo.nybestsellerbooks.data.model.Book
import com.natankayevo.nybestsellerbooks.data.response.BookBodyResponse
import com.natankayevo.nybestsellerbooks.data.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        APIService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {

            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful) {
                    var books: MutableList<Book> = mutableListOf()

                    response.body()?.let { bookBodyResponse ->
                        books = getBooksFromBookBody(bookBodyResponse)
                    }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getBooksFromBookBody(bookBody: BookBodyResponse): MutableList<Book> {
        val books: MutableList<Book> = mutableListOf()

            for (book in bookBody.booksResults) {

                book.bookDetails[0].let { bookDetails ->
                    val bookResult = Book(
                        bookDetails.title,
                        bookDetails.author
                    )

                    books.add(bookResult)
                }
            }

        return books
    }
}
