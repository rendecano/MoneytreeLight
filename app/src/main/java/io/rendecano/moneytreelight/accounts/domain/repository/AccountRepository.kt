package io.rendecano.moneytreelight.accounts.domain.repository

import io.rendecano.moneytreelight.accounts.domain.model.Account

interface AccountRepository {

    suspend fun getAccountsList() : List<Account>
}