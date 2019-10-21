package io.rendecano.moneytreelight.accounts.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.rendecano.moneytreelight.accounts.domain.interactor.GetAccountsList
import io.rendecano.moneytreelight.accounts.domain.interactor.GetAccountsTotal
import io.rendecano.moneytreelight.accounts.domain.model.Account
import io.rendecano.moneytreelight.accounts.domain.model.AccountTotal
import io.rendecano.moneytreelight.common.domain.model.Failure
import io.rendecano.moneytreelight.common.presentation.SingleLiveEvent
import javax.inject.Inject

class AccountsListViewModel @Inject constructor(private val getAccountsList: GetAccountsList,
                                                private val getAccountsTotal: GetAccountsTotal) : ViewModel() {

    val accountsList = MutableLiveData<List<Account>>()
    val accountsTotal = MutableLiveData<AccountTotal>()
    val error = SingleLiveEvent<String>()
    val loading = MutableLiveData<Boolean>()

    fun getAccounts() {
        loading.value = true
        getAccountsTotal.invoke(viewModelScope, GetAccountsTotal.Params()) { it.either(::handleFailure, ::handleAccountTotalSuccess) }
        getAccountsList.invoke(viewModelScope, GetAccountsList.Params()) { it.either(::handleFailure, ::handleAccountListSuccess) }
    }

    private fun handleAccountTotalSuccess(accountTotal: AccountTotal) {
        accountsTotal.value = accountTotal
    }

    private fun handleAccountListSuccess(list: List<Account>) {
        accountsList.value = list
        loading.value = false
    }

    private fun handleFailure(exception: Failure) {
        error.value = exception.exception.message
        loading.value = false
    }
}