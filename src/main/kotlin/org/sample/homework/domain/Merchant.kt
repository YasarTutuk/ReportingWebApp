package org.sample.homework.domain

data class Merchant(
        val id: Int,
        val merchantId: String?,
        val name: String?,
        val originalAmount: Int?,
        val originalCurrency: Int?,
        val referenceNo: String?,
        val status: Status?,
        val operation: Operation?,
        val message: String?,
        val created_at: String?,
        val updated_at: String?,
        val transactionId: String?,
        val channel: String?,
        val chainId: String?,
        val customData: String?,
        val agentInfoId: String?,
        val fxTransactionId: String?,
        val acquirerId: Int?,
        val code: String?
        //val agent: Agent?
)