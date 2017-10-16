package org.sample.homework.controller

import org.sample.homework.domain.TokenStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionListController {
    @Autowired
    private lateinit var token : TokenStatus

}