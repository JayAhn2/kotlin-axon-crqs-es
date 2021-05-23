package com.example.axon.command.service

import com.example.axon.command.command.CreateAccountCommand
import com.example.axon.command.command.DepositMoneyCommand
import com.example.axon.command.command.WithdrawMoneyCommand
import com.example.axon.command.dto.CreateAccountRequest
import com.example.axon.command.dto.DepositRequest
import com.example.axon.command.dto.WithdrawalRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture


@Service
class AccountCommandService(private val commandGateway: CommandGateway) {
    fun createAccount(request: CreateAccountRequest): CompletableFuture<String> {
        return commandGateway.send(
            CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.startingBalance
            )
        )
    }

    fun depositToAccount(request: DepositRequest): CompletableFuture<String> {
        return commandGateway.send(
            DepositMoneyCommand(
                request.accountId,
                request.amount
            )
        )
    }

    fun withdrawFromAccount(request: WithdrawalRequest): CompletableFuture<String> {
        return commandGateway.send(
            WithdrawMoneyCommand(
                request.accountId,
                request.amount
            )
        )
    }
}
