package org.sample.homework.domain

data class CustomerInfo(
        val id: Int,
        val created_at: String,
        val updated_at: String?,
        val deleted_at: String?,
        val number: String?,
        val expiryMonth: String?,
        val expiryYear: String?,
        val startMonth: String?,
        val startYear: String?,
        val issueNumber: String?,
        val email: String?,
        val birthday: String?,
        val gender: String?,
        val billingTitle: String?,
        val billingFirstName: String?,
        val billingLastName: String?,
        val billingCompany: String?,
        val billingAddress1: String?,
        val billingAddress2: String?,
        val billingCity: String?,
        val billingPostcode: String?,
        val billingState: String?,
        val billingCountry: String?,
        val billingPhone: String?,
        val billingFax: String?,
        val shippingTitle: String?,
        val shippingFirstName: String?,
        val shippingLastName: String?,
        val shippingCompany: String?,
        val shippingAddress1: String?,
        val shippingAddress2: String?,
        val shippingCity: String?,
        val shippingPostcode: String?,
        val shippingState: String?,
        val shippingCountry: String?,
        val shippingPhone: String?,
        val shippingFax: String?
)