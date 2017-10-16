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
class TransactionListController(val client: CommonClient) {
    @Autowired
    private lateinit var token: TokenStatus

    @GetMapping("/list")
    fun report(@RequestParam(value = "from", required = false) from: String?,
               @RequestParam(value = "to", required = false) to: String?,
               @RequestParam(value = "status", required = false) status: Status?,
               @RequestParam(value = "operation", required = false) operation: Operation?,
               @RequestParam(value = "merchantId", required = false) merchant: Int?,
               @RequestParam(value = "acquirerId", required = false) acquirer: Int?,
               @RequestParam(value = "paymentMethod", required = false) method: PaymentMethod?,
               @RequestParam(value = "errorCode", required = false) code: String?,
               @RequestParam(value = "filterBy", required = false) filterBy: String?,
               @RequestParam(value = "filterValue", required = false) filterValue: String?,
               @RequestParam(value = "page", required = false) page: Int?
    ): ResponseEntity<Any> {
        client.endPoint = "/transaction/list"
        client.addHeader("Authorization", token.value)

        val request = TransactionListRequest(from, to, status, operation, merchant, acquirer, method, code, filterBy, filterValue, page ?: 1)
        val (response, error) = client.post(request, TransactionListResponse::class.java)

        return if (response != null) ResponseEntity(response, HttpStatus.OK)
        else ResponseEntity(error as Any, HttpStatus.OK)
    }
}