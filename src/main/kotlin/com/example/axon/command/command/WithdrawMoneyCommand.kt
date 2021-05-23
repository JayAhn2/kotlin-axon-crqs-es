package com.example.axon.command.command

import java.math.BigDecimal

class WithdrawMoneyCommand(
    id: String,
    val amount: BigDecimal
) : BaseCommand<String>(id)
