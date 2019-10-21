package io.rendecano.moneytreelight.accounts.domain.interactor

import io.rendecano.moneytreelight.accounts.domain.model.Account
import io.rendecano.moneytreelight.accounts.domain.repository.AccountRepository
import io.rendecano.moneytreelight.common.domain.interactor.BaseUseCase
import io.rendecano.moneytreelight.common.domain.model.Either
import io.rendecano.moneytreelight.common.domain.model.Failure
import javax.inject.Inject

class GetAccountsList @Inject constructor(private val accountRepository: AccountRepository) : BaseUseCase<List<Account>, GetAccountsList.Params>() {

    override suspend fun run(params: Params?): Either<Failure, List<Account>> {
        return try {
            // FIXME: We could pass the userId to get the accounts List OR depends on the
            // business requirements (i.e. if user session is in place, userId isn't needed)
            Either.Right(accountRepository.getAccountsList())
        } catch (exception: Exception) {
            Either.Left(Failure.FeatureFailure())
        }
    }

    class Params
}