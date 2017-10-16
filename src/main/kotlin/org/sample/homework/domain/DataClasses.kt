package org.sample.homework.domain

import java.time.LocalDateTime

data class HttpError(val httpCode: String, val message: String)

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val status: String)

data class TRResponse(val count: Int, val total: Int, val currency: String)
data class TransactionReportRequest(val fromDate: String, val toDate: String, val merchant: Int? = null, val acquirer: Int? = null)
data class TransactionReportResponse(val status: String, val response: Array<TRResponse>)

data class CustomerInfo(
        val id: Int,
        val created_at: String,
        val updated_at: String,
        val deleted_at: String,
        val number: String
        //..
)
data class ClientRequest(val transactionId: String)
data class ClientResponse(val customerInfo: CustomerInfo)

data class tok (val a: String, val b: LocalDateTime)