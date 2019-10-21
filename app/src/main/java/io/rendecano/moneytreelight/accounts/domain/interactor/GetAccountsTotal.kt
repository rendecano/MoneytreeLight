package io.rendecano.moneytreelight.accounts.domain.interactor

import io.rendecano.moneytreelight.accounts.domain.model.AccountTotal
import io.rendecano.moneytreelight.accounts.domain.repository.AccountRepository
import io.rendecano.moneytreelight.common.domain.interactor.BaseUseCase
import io.rendecano.moneytreelight.common.domain.model.Either
import io.rendecano.moneytreelight.common.domain.model.Failure
import javax.inject.Inject

class GetAccountsTotal @Inject constructor(private val accountRepository: AccountRepository) : BaseUseCase<AccountTotal, GetAccountsTotal.Params>() {

    override suspend fun run(params: Params?): Either<Failure, AccountTotal> {
        return try {

            // FIXME: We could get something like account preferences or details which contains the default
            // currency information
            val defaultAccountCurrency = "JPY"
            val accountTotal = accountRepository.getAccountsList().map { it.currentBalance }.sum()
            Either.Right(AccountTotal(defaultAccountCurrency, accountTotal))

        } catch (exception: Exception) {
            Either.Left(Failure.FeatureFailure())
        }
    }

    class Params
}