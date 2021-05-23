package com.example.axon.command.dto

import java.math.BigDecimal

data class WithdrawalRequest(
    val accountId: String,
    val amount: BigDecimal
)
