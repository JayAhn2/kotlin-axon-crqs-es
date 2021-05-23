package com.example.axon.common.event

import java.math.BigDecimal

class AccountDebitedEvent(
    id: String,
    val amount: BigDecimal
) : BaseEvent<String>(id)
