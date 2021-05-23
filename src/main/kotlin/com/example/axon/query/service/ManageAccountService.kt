package com.example.axon.query.service

import com.example.axon.common.event.AccountActivatedEvent
import com.example.axon.common.event.AccountCreatedEvent
import com.example.axon.common.event.AccountCreditedEvent
import com.example.axon.common.event.AccountDebitedEvent
import com.example.axon.query.entity.Account
import com.example.axon.query.query.FindAccountByIdQuery
import com.example.axon.query.repository.AccountRepository
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service


@Service
class ManageAccountService(private val accountRepository: AccountRepository) {
    @EventHandler
    fun on(event: AccountCreatedEvent) {
        val account = Account()
        account.accountId = event.id
        account.balance = event.balance
        account.status = "CREATED"
        accountRepository.save(account)
    }

    @EventHandler
    fun on(event: AccountActivatedEvent) {
        val account = accountRepository.findById(event.id).orElse(null)
        if (account != null) {
            account.status = event.status
            accountRepository.save(account)
        }
    }

    @EventHandler
    fun on(event: AccountCreditedEvent) {
        val account = accountRepository.findById(event.id).orElse(null)
        if (account != null) {
            account.balance = account.balance.add(event.amount)

            accountRepository.save(account)
        }
    }

    @EventHandler
    fun on(event: AccountDebitedEvent) {
        val account = accountRepository.findById(event.id).orElse(null)
        if (account != null) {
            account.balance = account.balance.subtract(event.amount)
            accountRepository.save(account)
        }
    }

    @QueryHandler
    fun handle(query: FindAccountByIdQuery): Account {
        return accountRepository.findById(query.accountId).orElse(null)
    }
}
