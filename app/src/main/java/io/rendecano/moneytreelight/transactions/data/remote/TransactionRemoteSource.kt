package io.rendecano.moneytreelight.transactions.data.remote

import io.rendecano.moneytreelight.transactions.data.remote.model.TransactionList

interface TransactionRemoteSource {
    suspend fun getTransactions(accountId: Long): TransactionList
}