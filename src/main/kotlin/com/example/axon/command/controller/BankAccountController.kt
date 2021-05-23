package com.example.axon.command.controller

import com.example.axon.command.dto.CreateAccountRequest
import com.example.axon.command.dto.DepositRequest
import com.example.axon.command.dto.WithdrawalRequest
import com.example.axon.command.service.AccountCommandService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/bank-account")
class BankAccountController(private val accountCommandService: AccountCommandService) {
    @PostMapping("/create")
    fun createAccount(@RequestBody request: CreateAccountRequest): ResponseEntity<String> {
        return try {
            val response = accountCommandService.createAccount(request)
            ResponseEntity(response.get(), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/deposit")
    fun deposit(@RequestBody request: DepositRequest): ResponseEntity<String> {
        return try {
            accountCommandService.depositToAccount(request)
            ResponseEntity("Amount credited", HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/withdraw")
    fun withdraw(@RequestBody request: WithdrawalRequest): ResponseEntity<String> {
        return try {
            accountCommandService.withdrawFromAccount(request)
            ResponseEntity("Amount debited.", HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
