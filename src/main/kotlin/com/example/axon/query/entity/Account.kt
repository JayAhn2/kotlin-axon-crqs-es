package com.example.axon.query.entity

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account {
    @Id
    lateinit var accountId: String
    lateinit var balance: BigDecimal
    lateinit var status: String
}
