package io.rendecano.moneytreelight.transactions.data.remote.model

import com.google.gson.annotations.SerializedName

data class TransactionRemote(
        @SerializedName("account_id") val accountId: Long = 0,
        val amount: Double = 0.0,
        @SerializedName("category_id") val categoryId: Long = 0,
        val date: String = "",
        val description: String = "",
        val id: Long = 0)