package io.rendecano.moneytreelight.transactions.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.rendecano.moneytreelight.transactions.data.remote.TransactionRemoteSource
import io.rendecano.moneytreelight.transactions.data.remote.impl.TransactionRemoteDataSource
import javax.inject.Singleton

@Module
class TransactionBindingModule {

    @Provides
    @Singleton
    internal fun provideTransactionRemoteSource(context: Context, gson: Gson): TransactionRemoteSource {
        return TransactionRemoteDataSource(context.resources, gson)
    }
}