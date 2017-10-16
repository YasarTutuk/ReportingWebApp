package org.sample.homework.controller

import org.sample.homework.domain.TokenStatus
import org.sample.homework.domain.TransactionReportRequest
import org.sample.homework.domain.TransactionReportResponse
import org.sample.homework.service.CommonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class ReportController(val client: CommonClient) {
    @Autowired
    private lateinit var token: TokenStatus

    @GetMapping("/report")
    fun report(@RequestParam(value = "from", defaultValue = "2000-01-01") from: String,
               @RequestParam(value = "to", required = false) to: String?,
               @RequestParam(value = "merchantId", required = false) merchant: Int?,
               @RequestParam(value = "acquirerId", required = false) acquirer: Int?,
               @RequestParam(value = "currency", required = false) currency: String?): ResponseEntity<Any> {
        client.endPoint = "/transactions/report"
        client.addHeader("Authorization", token.value)

        val request = TransactionReportRequest(from, to ?: LocalDateTime.now().toString(), merchant, acquirer)
        val (response, error) = client.post(request, TransactionReportResponse::class.java)

        return if (response != null) ResponseEntity(applyFilter(currency, response), HttpStatus.OK)
        else ResponseEntity(error as Any, HttpStatus.OK)
    }

    private fun applyFilter(currency: String?, response: TransactionReportResponse): TransactionReportResponse {
        if (currency == null) return response

        val filtered = response.response
                .filter { it.currency.toLowerCase() == currency.toLowerCase() }
                .toTypedArray()
        return TransactionReportResponse(response.status, filtered)
    }
}