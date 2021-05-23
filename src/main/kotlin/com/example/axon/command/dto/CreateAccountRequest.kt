package com.example.axon.command.dto

import java.math.BigDecimal

data class CreateAccountRequest(
    val startingBalance: BigDecimal
)
