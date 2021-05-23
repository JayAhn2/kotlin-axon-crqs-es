package com.example.axon.command.command

import java.math.BigDecimal

class CreateAccountCommand(
    id: String,
    val balance: BigDecimal
) : BaseCommand<String>(id)
