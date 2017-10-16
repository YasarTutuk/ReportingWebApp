package org.sample.homework.controller

import org.sample.homework.domain.*
import org.sample.homework.service.CommonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController(val client: CommonClient) {
    @Autowired
    private lateinit var token: TokenStatus

    @GetMapping("/transaction")
    fun transaction(@RequestParam(value = "id", defaultValue = "1-1444392550-1") transactionId: String): ResponseEntity<Any> {
        client.endPoint = "/transaction"
        client.addHeader("Authorization", token.value)

        val request = TransactionRequest(transactionId)
        val (response, error) = client.post(request, TransactionResponse::class.java)

        return if (response != null) ResponseEntity(response, HttpStatus.OK)
        else ResponseEntity(error as Any, HttpStatus.OK)
    }
}