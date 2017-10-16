package org.sample.homework.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
//import java.util.concurrent.atomic.AtomicLong

import org.sample.homework.domain.LoginRequest
import org.sample.homework.domain.LoginResponse
import org.sample.homework.domain.TokenStatus
import org.sample.homework.service.CommonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import javax.servlet.http.HttpSession

@RestController
class LoginController(val client: CommonClient){

    @Autowired
    private lateinit var token : TokenStatus
//    val counter = AtomicLong()

    @GetMapping("/login")
    fun login(session: HttpSession, @RequestParam(value = "email", defaultValue = "demo@bumin.com.tr") email: String,
              @RequestParam(value = "password", defaultValue = "cjaiU8CV") password: String) : ResponseEntity<Any> {
//        counter.incrementAndGet()
        client.endPoint = "/merchant/user/login"

        val request = LoginRequest(email, password)
        val (response, error) = this.client.post(request, LoginResponse::class.java)

        return if (response != null) {
            session.setAttribute("token", response.token)
            session.setAttribute("validTo", LocalDateTime.now().plusMinutes(10))
            token.value = response.token
            token.validTo = LocalDateTime.now().plusMinutes(10)

            ResponseEntity(response, HttpStatus.OK)
        }
        else ResponseEntity(error as Any, HttpStatus.OK)
    }
}
