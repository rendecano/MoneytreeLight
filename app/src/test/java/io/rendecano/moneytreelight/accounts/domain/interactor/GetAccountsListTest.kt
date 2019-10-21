package io.rendecano.moneytreelight.accounts.domain.interactor

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.rendecano.moneytreelight.accounts.domain.model.Account
import io.rendecano.moneytreelight.accounts.domain.repository.AccountRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetAccountsListTest {

    @Mock
    private lateinit var accountRepository: AccountRepository
    lateinit var getAccountsList: GetAccountsList

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAccountsList = GetAccountsList(accountRepository)
    }

    @Test
    fun `test get accounts list with no accounts`() {
        runBlocking {
            // Given
            given(accountRepository.getAccountsList()).willReturn(listOf())

            // When
            val result = getAccountsList.run(GetAccountsList.Params())

            // Then
            verify(accountRepository).getAccountsList()
            verifyNoMoreInteractions(accountRepository)
            Assert.assertTrue(result.isRight)
            result.either({},
                    { right ->
                        Assert.assertTrue(right.isEmpty())
                    })

        }
    }

    @Test
    fun `test get accounts total with valid accounts`() {
        runBlocking {
            val accountOne = Account(name = "Starbucks card", currency = "USD", currentBalance = 200.50)
            val accountTwo = Account(name = "Woolies card", currency = "JPY", currentBalance = 134.80)

            // Given
            given(accountRepository.getAccountsList()).willReturn(listOf(accountOne, accountTwo))

            // When
            val result = getAccountsList.run(GetAccountsList.Params())

            // Then
            verify(accountRepository).getAccountsList()
            verifyNoMoreInteractions(accountRepository)
            Assert.assertTrue(result.isRight)
            result.either({},
                    { right ->
                        Assert.assertTrue(right.isNotEmpty())
                        Assert.assertTrue(right[1] === accountTwo)
                    })

        }
    }
}