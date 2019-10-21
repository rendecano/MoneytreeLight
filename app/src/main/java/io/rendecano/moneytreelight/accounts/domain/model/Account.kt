package io.rendecano.moneytreelight.accounts.domain.model

data class Account(val id: Long = 0,
                   val name: String = "",
                   val currency: String = "",
                   val currentBalance: Double = 0.0,
                   val currentBaseBalance: Double = 0.0,
                   val institutionName: String = "")