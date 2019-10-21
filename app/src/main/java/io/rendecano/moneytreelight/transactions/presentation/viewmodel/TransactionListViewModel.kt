package io.rendecano.moneytreelight.transactions.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.rendecano.moneytreelight.common.domain.model.Failure
import io.rendecano.moneytreelight.common.presentation.SingleLiveEvent
import io.rendecano.moneytreelight.transactions.domain.interactor.GetTransactionList
import io.rendecano.moneytreelight.transactions.domain.model.Transaction
import javax.inject.Inject

class TransactionListViewModel @Inject constructor(private val getTransactionList: GetTransactionList) : ViewModel() {

    val transactions = MutableLiveData<List<Transaction>>()
    val error = SingleLiveEvent<String>()
    val loading = MutableLiveData<Boolean>()

    fun getTransactions(accountId: Long) {
        loading.value = true
        getTransactionList.invoke(viewModelScope, GetTransactionList.Params(accountId)) { it.either(::handleFailure, ::handleAccountTotalSuccess) }
    }

    private fun handleAccountTotalSuccess(transactionList: List<Transaction>) {
        transactions.value = transactionList
        loading.value = false
    }

    private fun handleFailure(exception: Failure) {
        error.value = exception.exception.message
        loading.value = false
    }
}