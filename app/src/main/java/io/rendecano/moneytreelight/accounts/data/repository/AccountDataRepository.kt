package io.rendecano.moneytreelight.accounts.data.repository

import io.rendecano.moneytreelight.accounts.data.remote.model.AccountRemote
import io.rendecano.moneytreelight.accounts.data.remote.source.AccountRemoteSource
import io.rendecano.moneytreelight.accounts.domain.model.Account
import io.rendecano.moneytreelight.accounts.domain.repository.AccountRepository
import javax.inject.Inject

class AccountDataRepository @Inject constructor(private val accountRemoteSource: AccountRemoteSource) : AccountRepository {

    override suspend fun getAccountsList(): List<Account> {

        val accountRemoteList = accountRemoteSource.getAccountsList()
        // TODO: Save account list to local repository
        return accountRemoteList.accountRemoteList.map { it.toAccount() }
    }

    private fun AccountRemote.toAccount() = Account(this.id,
            this.name,
            this.currency,
            this.currentBalance,
            this.currentBalanceInBase)
}