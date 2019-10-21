package io.rendecano.moneytreelight.transactions.data.remote.impl

import android.content.res.Resources
import com.google.gson.Gson
import io.rendecano.moneytreelight.transactions.data.remote.TransactionRemoteSource
import io.rendecano.moneytreelight.transactions.data.remote.model.TransactionList
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class TransactionRemoteDataSource @Inject constructor(private val resources: Resources,
                                                      private val gson: Gson) : TransactionRemoteSource {

    override suspend fun getTransactions(accountId: Long): TransactionList {
        // TODO: Hardcoded transaction fetching for specific accountId
        return gson.fromJson(readJsonFromAsset("transactions_$accountId.json"), TransactionList::class.java)
    }

    // TODO: We could implement a network call to fetch the actual transactions from the server
    // override suspend fun getTransactions(accountId: Long): TransactionList {
    //  Any networking library that like Retrofit, Volley, etc.
    // }

    @Throws(IOException::class)
    private fun readJsonFromAsset(fileName: String): String {
        val inputStream: InputStream = resources.assets.open(fileName)
        return inputStream.bufferedReader().use { it.readText() }
    }
}