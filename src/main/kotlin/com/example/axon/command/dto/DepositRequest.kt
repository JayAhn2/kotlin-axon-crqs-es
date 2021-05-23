package com.example.axon.command.dto

import java.math.BigDecimal

data class DepositRequest(
    val accountId: String,
    val amount: BigDecimal
)
