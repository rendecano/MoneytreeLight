package io.rendecano.moneytreelight.accounts.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.rendecano.moneytreelight.accounts.data.repository.AccountDataRepository
import io.rendecano.moneytreelight.accounts.domain.repository.AccountRepository
import io.rendecano.moneytreelight.accounts.presentation.ui.AccountsListFragment
import io.rendecano.moneytreelight.accounts.presentation.viewmodel.AccountsListViewModel
import io.rendecano.moneytreelight.common.di.ViewModelKey

@Module
abstract class AccountModule {

    @ContributesAndroidInjector
    abstract fun contributeStockListFragment(): AccountsListFragment

    @Binds
    @IntoMap
    @ViewModelKey(AccountsListViewModel::class)
    internal abstract fun bindAccountsListViewModel(accountsListViewModel: AccountsListViewModel): ViewModel

    @Binds
    abstract fun bindAccountRepository(accountDataRepository: AccountDataRepository): AccountRepository
}