package com.natankayevo.nybestsellerbooks.presentation.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.natankayevo.nybestsellerbooks.R
import com.natankayevo.nybestsellerbooks.data.model.Book
import com.natankayevo.nybestsellerbooks.data.repository.BooksRepository
import com.natankayevo.nybestsellerbooks.data.response.BookBodyResponse
import com.natankayevo.nybestsellerbooks.data.response.BooksResult
import com.natankayevo.nybestsellerbooks.data.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class BooksViewModel(var dataSource: BooksRepository) : ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val error: MutableLiveData<Int> = MutableLiveData()

    fun getBooks() {
        dataSource.getBooks { result: BooksResult ->
             when(result){
                 is BooksResult.Success -> {
                     booksLiveData.value = result.books
                 }
                 is BooksResult.ServerError ->{
                    error.value = R.string.data_error_500
                 }
             }

        }
    }

    class ViewModelFactory(private val dataSource: BooksRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(BooksViewModel::class.java)){
                return BooksViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
