package io.rendecano.moneytreelight.transactions.domain.interactor

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.rendecano.moneytreelight.transactions.domain.model.Transaction
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

class GetTransactionListTest {
    @Mock
    private lateinit var transactionRepository: TransactionRepository
    lateinit var getTransactionList: GetTransactionList

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTransactionList = GetTransactionList(transactionRepository)
    }

    @Test
    fun `test get transaction list for account with no transactions`() {
        runBlocking {
            // Given
            given(transactionRepository.getTransactions(0)).willReturn(listOf())

            // When
            val result = getTransactionList.run(GetTransactionList.Params(0))

            // Then
            verify(transactionRepository).getTransactions(0)
            verifyNoMoreInteractions(transactionRepository)
            assertTrue(result.isRight)
            result.either({},
                    { right ->
                        assertTrue(right.isEmpty())
                    })

        }
    }

    @Test
    fun `test get transaction list for account with transactions`() {
        runBlocking {
            val transactionOne = Transaction(accountId = 0, categoryId = 1, amount = 200.50, description = "This is a description one")
            val transactionTwo = Transaction(accountId = 0, categoryId = 2, amount = 55.78, description = "This is a description two")

            // Given
            given(transactionRepository.getTransactions(0)).willReturn(listOf(transactionOne, transactionTwo))

            // When
            val result = getTransactionList.run(GetTransactionList.Params(0))

            // Then
            verify(transactionRepository).getTransactions(0)
            verifyNoMoreInteractions(transactionRepository)
            assertTrue(result.isRight)
            result.either({},
                    { right ->
                        assertTrue(right.isNotEmpty())
                        assertTrue(right[1] === transactionTwo)
                    })

        }
    }
}