package com.example.axon.command.command

import java.math.BigDecimal

class DepositMoneyCommand(
    id: String,
    val amount: BigDecimal
) : BaseCommand<String>(id)
