package org.sample.homework.domain

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val status: String)

data class TransactionReportRequest(val fromDate: String, val toDate: String, val merchant: Int? = null, val acquirer: Int? = null)
data class TransactionReportResponse(val status: String, val response: Array<TransactionReportData>)

data class TransactionListRequest(
        val fromDate: String?,
        val toDate: String?,
        val status: Status?,
        val operation: Operation?,
        val merchantId: Int?,
        val acquirerId: Int?,
        val paymentMethod: PaymentMethod?,
        val errorCode: String?,
        val filterField: String?,
        val filterValue: String?,
        val page: Int?
)

data class TransactionListResponse(
        val per_page: Int?,
        val current_page: Int?,
        val next_page_url: String?,
        val prev_page_url: String?,
        val from: Int?,
        val to: Int?,
        val data: Array<TransactionListData>
)

data class TransactionRequest(val transactionId: String)
data class TransactionResponse(
        val fx: Fx?,
        val customerInfo: CustomerInfo?,
        val merchant: Merchant?,
        val transaction: Transaction?
)

data class ClientRequest(val transactionId: String)
data class ClientResponse(val customerInfo: CustomerInfo)
