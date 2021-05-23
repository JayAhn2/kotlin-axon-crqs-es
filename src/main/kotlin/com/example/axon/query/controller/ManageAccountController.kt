package com.example.axon.query.controller

import com.example.axon.query.entity.Account
import com.example.axon.query.query.FindAccountByIdQuery
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/manage-account")
class ManageAccountController(private val queryGateway: QueryGateway) {
    @GetMapping("/get-account")
    fun getAccount(@RequestParam id: String): ResponseEntity<Account> {
        val account = queryGateway.query(FindAccountByIdQuery(id), Account::class.java)
            .join() ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(account, HttpStatus.OK)
    }
}
