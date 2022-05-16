package com.natankayevo.nybestsellerbooks.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natankayevo.nybestsellerbooks.data.model.Book

class BooksViewModel : ViewModel() {
    val books : MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(){
        books.value = getBooksFromDataLayer()
    }

    fun getBooksFromDataLayer(): List<Book> = listOf(
        Book("TitleA", "AuthorA"),
        Book("TitleB", "AuthorB"),
        Book("TitleC", "AuthorC")
    )


}
