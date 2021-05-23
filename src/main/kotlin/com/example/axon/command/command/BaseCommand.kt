package com.example.axon.command.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

open class BaseCommand<T>(
    @TargetAggregateIdentifier val id: T
)
