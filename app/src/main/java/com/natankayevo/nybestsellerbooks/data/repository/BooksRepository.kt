package com.natankayevo.nybestsellerbooks.data.repository

import com.natankayevo.nybestsellerbooks.data.response.BooksResult

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}