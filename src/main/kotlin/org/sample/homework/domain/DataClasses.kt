package org.sample.homework.domain

data class HttpError(val httpCode: String, val message: String)

data class TransactionReportData(val count: Int, val total: Int, val currency: String)
data class Transaction(val merchant: Merchant)
data class Fx(val merchant: Merchant?)
data class Ipn(val ipn: Boolean?)
data class TransactionListData(
        val data: Fx?,
        val customerInfo: CustomerInfo?,
        val merchant: Merchant?,
        val ipn: Ipn?,
        val transaction: Transaction?,
        val acquirer: Acquirer?,
        val refundable: Boolean?
)

