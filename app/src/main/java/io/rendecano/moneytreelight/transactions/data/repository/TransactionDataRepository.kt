package io.rendecano.moneytreelight.transactions.data.repository

import io.rendecano.moneytreelight.transactions.data.remote.TransactionRemoteSource
import io.rendecano.moneytreelight.transactions.data.remote.model.TransactionRemote
import io.rendecano.moneytreelight.transactions.domain.model.Transaction
import io.rendecano.moneytreelight.transactions.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionDataRepository @Inject constructor(private val transactionRemoteSource: TransactionRemoteSource) : TransactionRepository {

    override suspend fun getTransactions(accountId: Long): List<Transaction> =
        transactionRemoteSource.getTransactions(accountId).transactions.map { it.toTransaction() }


    override suspend fun searchTransaction(keyword: String): List<Transaction> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun TransactionRemote.toTransaction() =
            Transaction(this.accountId,
                    this.amount,
                    this.categoryId,
                    this.date,
                    this.description,
                    this.id)
}