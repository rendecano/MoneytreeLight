package io.rendecano.moneytreelight.accounts.data.remote.model

import com.google.gson.annotations.SerializedName

data class AccountRemote(
        val currency: String = "",
        @SerializedName("current_balance") val currentBalance: Double = 0.0,
        @SerializedName("current_balance_in_base") val currentBalanceInBase: Double = 0.0,
        val id: Long = 0,
        val institution: String = "",
        val name: String = "")
