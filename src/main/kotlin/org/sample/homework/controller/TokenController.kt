package org.sample.homework.controller

import org.sample.homework.domain.TokenStatus
import org.sample.homework.service.Serializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.servlet.http.HttpSession

@RestController
class TokenController(val serializer: Serializer) {
    @Autowired
    lateinit var token: TokenStatus

    @GetMapping("/token")
    fun token(session: HttpSession): Any {
        return when {
            token.value.isEmpty() -> "Token missing"
            token.validTo < LocalDateTime.now() -> "Token expired"
        //else -> session.getAttribute("token")
            else -> serializer.serialize(TokenStatus(token.value, token.validTo))
        }
    }
}