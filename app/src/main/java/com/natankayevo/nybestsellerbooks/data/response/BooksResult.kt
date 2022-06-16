package com.natankayevo.nybestsellerbooks.data.response

import com.natankayevo.nybestsellerbooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>): BooksResult()
    class APIError(val statusCode: Int): BooksResult()
    object ServerError: BooksResult()
}