package com.example.axon.command.aggregate

import com.example.axon.command.command.CreateAccountCommand
import com.example.axon.command.command.DepositMoneyCommand
import com.example.axon.command.command.WithdrawMoneyCommand
import com.example.axon.common.event.AccountActivatedEvent
import com.example.axon.common.event.AccountCreatedEvent
import com.example.axon.common.event.AccountCreditedEvent
import com.example.axon.common.event.AccountDebitedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal


@Aggregate
class AccountAggregate {
    @AggregateIdentifier
    private lateinit var accountId: String
    private lateinit var balance: BigDecimal
    private lateinit var status: String

    constructor()

    @CommandHandler
    constructor(command: CreateAccountCommand) {
        AggregateLifecycle.apply(
            AccountCreatedEvent(
                command.id,
                command.balance
            )
        )
    }

    @EventSourcingHandler
    fun on(event: AccountCreatedEvent) {
        accountId = event.id
        balance = event.balance
        status = "CREATED"
        AggregateLifecycle.apply(AccountActivatedEvent(accountId, "ACTIVATED"))
    }

    @EventSourcingHandler
    fun on(event: AccountActivatedEvent) {
        status = event.status
    }

    @CommandHandler
    fun on(command: DepositMoneyCommand) {
        AggregateLifecycle.apply(
            AccountCreditedEvent(
                command.id,
                command.amount
            )
        )
    }

    @EventSourcingHandler
    fun on(event: AccountCreditedEvent) {
        balance = balance.add(event.amount)
    }

    @CommandHandler
    fun on(command: WithdrawMoneyCommand) {
        AggregateLifecycle.apply(
            AccountDebitedEvent(
                command.id,
                command.amount
            )
        )
    }

    @EventSourcingHandler
    fun on(event: AccountDebitedEvent) {
        balance = balance.subtract(event.amount)
    }
}
