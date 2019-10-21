package io.rendecano.moneytreelight.transactions.domain.interactor

import io.rendecano.moneytreelight.common.domain.interactor.BaseUseCase
import io.rendecano.moneytreelight.common.domain.model.Either
import io.rendecano.moneytreelight.common.domain.model.Failure
import io.rendecano.moneytreelight.transactions.domain.model.Transaction
import io.rendecano.moneytreelight.transactions.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionList @Inject constructor(private val transactionRepository: TransactionRepository) : BaseUseCase<List<Transaction>, GetTransactionList.Params>() {
    override suspend fun run(params: Params?): Either<Failure, List<Transaction>> {
        return try {
            val result = transactionRepository.getTransactions(params!!.accountId).sortedByDescending { it.date }
            Either.Right(result)
        } catch (exception: Exception) {
            Either.Left(Failure.FeatureFailure())
        }
    }

    data class Params(val accountId: Long = 0)
}