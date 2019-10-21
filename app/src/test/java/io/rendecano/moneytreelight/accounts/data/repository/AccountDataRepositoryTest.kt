package io.rendecano.moneytreelight.accounts.data.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.rendecano.moneytreelight.accounts.data.remote.model.AccountRemote
import io.rendecano.moneytreelight.accounts.data.remote.model.AccountsList
import io.rendecano.moneytreelight.accounts.data.remote.source.AccountRemoteSource
import io.rendecano.moneytreelight.accounts.domain.repository.AccountRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AccountDataRepositoryTest {

    @Mock
    private lateinit var accountRemoteSource: AccountRemoteSource
    lateinit var accountRepository: AccountRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accountRepository = AccountDataRepository(accountRemoteSource)
    }

    @Test
    fun `test get accounts remote list with no accounts`() {
        runBlocking {
            // Given
            given(accountRemoteSource.getAccountsList()).willReturn(AccountsList())

            // When
            val result = accountRepository.getAccountsList()

            // Then
            verify(accountRemoteSource).getAccountsList()
            verifyNoMoreInteractions(accountRemoteSource)
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `test get accounts remote list with valid accounts`() {
        runBlocking {
            val accountRemoteOne = AccountRemote(name = "Starbucks card", currency = "USD", currentBalance = 200.50)
            val accountRemoteTwo = AccountRemote(name = "Woolies card", currency = "JPY", currentBalance = 134.80)
            val accountsList = AccountsList(listOf(accountRemoteOne, accountRemoteTwo))

            // Given
            given(accountRemoteSource.getAccountsList()).willReturn(accountsList)

            // When
            val result = accountRepository.getAccountsList()

            // Then
            verify(accountRemoteSource).getAccountsList()
            verifyNoMoreInteractions(accountRemoteSource)
            assertTrue(result.size == 2)
            assertTrue(result[0].name == "Starbucks card")
        }
    }
}