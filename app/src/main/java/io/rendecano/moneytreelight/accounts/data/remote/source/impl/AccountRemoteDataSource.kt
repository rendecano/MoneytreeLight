package io.rendecano.moneytreelight.accounts.data.remote.source.impl

import android.content.res.Resources
import com.google.gson.Gson
import io.rendecano.moneytreelight.accounts.data.remote.model.AccountsList
import io.rendecano.moneytreelight.accounts.data.remote.source.AccountRemoteSource
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

private const val ACCOUNTS_LOCAL_FILENAME = "accounts.json"

class AccountRemoteDataSource @Inject constructor(private val resources: Resources,
                                                  private val gson: Gson) : AccountRemoteSource {

    override suspend fun getAccountsList(): AccountsList = gson.fromJson(readJsonFromAsset(), AccountsList::class.java)

    // TODO: We could implement a network call to fetch the actual accounts from the server
    // override suspend fun getAccountsList(): AccountsList {
    //  Any networking library that like Retrofit, Volley, etc.
    // }

    @Throws(IOException::class)
    private fun readJsonFromAsset(): String {
        val inputStream: InputStream = resources.assets.open(ACCOUNTS_LOCAL_FILENAME)
        return inputStream.bufferedReader().use { it.readText() }
    }
}