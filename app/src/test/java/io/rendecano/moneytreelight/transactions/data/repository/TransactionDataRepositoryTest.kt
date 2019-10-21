package io.rendecano.moneytreelight.transactions.data.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.rendecano.moneytreelight.transactions.data.remote.TransactionRemoteSource
import io.rendecano.moneytreelight.transactions.data.remote.model.TransactionList
import io.rendecano.moneytreelight.transactions.data.remote.model.TransactionRemote
import io.rendecano.moneytreelight.transactions.domain.repository.TransactionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class TransactionDataRepositoryTest {
    @Mock
    private lateinit var transactionRemoteSource: TransactionRemoteSource
    lateinit var transactionRepository: TransactionRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        transactionRepository = TransactionDataRepository(transactionRemoteSource)
    }

    @Test
    fun `test get accounts remote list with no accounts`() {
        runBlocking {
            // Given
            given(transactionRemoteSource.getTransactions(0)).willReturn(TransactionList())

            // When
            val result = transactionRepository.getTransactions(0)

            // Then
            verify(transactionRemoteSource).getTransactions(0)
            verifyNoMoreInteractions(transactionRemoteSource)
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `test get accounts remote list with valid accounts`() {
        runBlocking {
            val transactionRemoteOne = TransactionRemote(accountId = 0, categoryId = 1, amount = 200.50, description = "This is a description one")
            val transactionRemoteTwo = TransactionRemote(accountId = 0, categoryId = 2, amount = 2334.0, description = "This is a description two")
            val transactionList = TransactionList(listOf(transactionRemoteOne, transactionRemoteTwo))

            // Given
            given(transactionRemoteSource.getTransactions(0)).willReturn(transactionList)

            // When
            val result = transactionRepository.getTransactions(0)

            // Then
            verify(transactionRemoteSource).getTransactions(0)
            verifyNoMoreInteractions(transactionRemoteSource)
            assertTrue(result.size == 2)
            assertTrue(result[1].description == "This is a description two")
        }
    }
}