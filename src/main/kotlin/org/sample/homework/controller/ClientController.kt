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
class ClientController(val client: CommonClient) {
    @Autowired
    private lateinit var token : TokenStatus

    @GetMapping("/client")
    fun client(@RequestParam(value = "transactionId", defaultValue = "1-1444392550-1") transactionId: String) : ResponseEntity<Any> {
        client.endPoint = "/client"
        client.addHeader("Authorization", token.value)

        val request = ClientRequest(transactionId)
        val (response, error) = client.post(request, ClientResponse::class.java)

//        client.post("{\"transactionId\": \"$transactionId\"}", ClientResponse::class.java)
//                .let { response = it.first; error = it.second }

        return if (response != null) ResponseEntity(response as Any, HttpStatus.OK)
        else ResponseEntity(error as Any, HttpStatus.OK)
    }
}