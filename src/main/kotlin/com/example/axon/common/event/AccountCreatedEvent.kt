package com.example.axon.common.event

import java.math.BigDecimal

class AccountCreatedEvent(
    id: String,
    val balance: BigDecimal
) : BaseEvent<String>(id)
