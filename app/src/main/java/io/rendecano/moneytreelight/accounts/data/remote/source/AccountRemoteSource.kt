package io.rendecano.moneytreelight.accounts.data.remote.source

import io.rendecano.moneytreelight.accounts.data.remote.model.AccountsList

interface AccountRemoteSource {
    suspend fun getAccountsList(): AccountsList
}