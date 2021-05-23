package com.example.axon.common.event

import java.math.BigDecimal

class AccountCreditedEvent(
    id: String,
    val amount: BigDecimal
) : BaseEvent<String>(id)
