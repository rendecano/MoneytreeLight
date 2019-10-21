package io.rendecano.moneytreelight.transactions.data.remote.impl

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.rendecano.moneytreelight.common.di.ViewModelKey
import io.rendecano.moneytreelight.transactions.data.repository.TransactionDataRepository
import io.rendecano.moneytreelight.transactions.domain.repository.TransactionRepository
import io.rendecano.moneytreelight.transactions.presentation.ui.TransactionListFragment
import io.rendecano.moneytreelight.transactions.presentation.viewmodel.TransactionListViewModel

@Module
abstract class TransactionModule {

    @ContributesAndroidInjector
    abstract fun contributeTransactionListFragment(): TransactionListFragment

    @Binds
    @IntoMap
    @ViewModelKey(TransactionListViewModel::class)
    internal abstract fun bindTransactionListViewModel(transactionListViewModel: TransactionListViewModel): ViewModel

    @Binds
    abstract fun bindTransactionRepository(transactionDataRepository: TransactionDataRepository): TransactionRepository
}