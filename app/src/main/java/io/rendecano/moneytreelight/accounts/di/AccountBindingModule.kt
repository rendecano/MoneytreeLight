package io.rendecano.moneytreelight.accounts.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.rendecano.moneytreelight.accounts.data.remote.source.AccountRemoteSource
import io.rendecano.moneytreelight.accounts.data.remote.source.impl.AccountRemoteDataSource
import javax.inject.Singleton

@Module
class AccountBindingModule {

    @Provides
    @Singleton
    internal fun provideAccountRemoteSource(context: Context, gson: Gson): AccountRemoteSource {
        return AccountRemoteDataSource(context.resources, gson)
    }
}