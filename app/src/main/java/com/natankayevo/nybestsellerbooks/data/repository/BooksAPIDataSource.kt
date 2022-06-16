package com.natankayevo.nybestsellerbooks.data.repository

import com.natankayevo.nybestsellerbooks.data.model.Book
import com.natankayevo.nybestsellerbooks.data.response.BookBodyResponse
import com.natankayevo.nybestsellerbooks.data.response.BooksResult
import com.natankayevo.nybestsellerbooks.data.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksAPIDataSource: BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit){
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

                    // booksLiveData.value = books
                    booksResultCallback.invoke(BooksResult.Success(books))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }

        })
    }

    private fun getBooksFromBookBody(bookBody: BookBodyResponse): MutableList<Book> {
        val books: MutableList<Book> = mutableListOf()

        for (book in bookBody.booksResults) {

            book.bookDetails[0].let { bookDetails ->
                val bookResult = Book(
                    bookDetails.title,
                    bookDetails.author,
                    bookDetails.description
                )

                books.add(bookResult)
            }
        }

        return books
    }
}