package io.rendecano.moneytreelight.transactions.domain.repository

import io.rendecano.moneytreelight.transactions.domain.model.Transaction

interface TransactionRepository {
    suspend fun getTransactions(accountId: Long): List<Transaction>

    suspend fun searchTransaction(keyword: String): List<Transaction>
}