package io.rendecano.moneytreelight.transactions.domain.model

data class Transaction(val accountId: Long = 0,
                       val amount: Double = 0.0,
                       val categoryId: Long = 0,
                       var date: String = "",
                       val description: String = "",
                       val id: Long = 0)