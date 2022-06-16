package com.natankayevo.nybestsellerbooks.presentation.book

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.natankayevo.nybestsellerbooks.R
import com.natankayevo.nybestsellerbooks.data.model.Book
import com.natankayevo.nybestsellerbooks.data.repository.BooksRepository
import com.natankayevo.nybestsellerbooks.data.response.BooksResult
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

// instance all mocks in test class
@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    // to always use main thread, since tests uses virtual machine
    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Objeto falso/simulado = Mock object
    // Mock variable
    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var errorLiveDataObserver: Observer<Int>

    // instance all mocks in test class
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    private lateinit var viewModel: BooksViewModel

    @Test
    fun getBooksGetSuccessSetsLiveData() {
        // Arrange
        val books = listOf<Book>(
            Book("titleA", "authorA", "descriptionA"),
            Book("titleB", "authorB", "descriptionB")
        )
        val successResultMockRepo = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(successResultMockRepo)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)

    }

    @Test
    fun getAuthorizationError(){
        // Arrange

        val errorAuthMessage = R.string.error_401
        val errorStatusCode = 401

        val mockRepoErrorAuth = MockRepository(BooksResult.APIError(errorStatusCode))

        val viewModel = BooksViewModel(mockRepoErrorAuth)
        viewModel.error.observeForever(errorLiveDataObserver)

        // Action

        viewModel.getBooks()

        // Assert

        verify(errorLiveDataObserver).onChanged(errorAuthMessage)
    }
}

class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback.invoke(result)
    }

}