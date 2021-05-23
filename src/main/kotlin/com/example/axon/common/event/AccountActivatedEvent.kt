package com.example.axon.common.event

class AccountActivatedEvent(
    id: String,
    val status: String
) : BaseEvent<String>(id)
