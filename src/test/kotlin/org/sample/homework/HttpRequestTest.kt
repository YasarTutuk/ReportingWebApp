package org.sample.homework

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.sample.homework.controller.LoginController

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder
class HttpRequestTest {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var controller: LoginController

    @Test
    @Throws(Exception::class)
    fun contexLoads() {
        assertTrue(controller != null)
    }

    @Test
    @Throws(Exception::class)
    fun shouldLogin() {
        val result = restTemplate!!.getForObject<String>("http://localhost:$port/login", String::class.java)
        assertTrue(result.contains("token"))
    }

    @Test
    fun shouldGetReport() {
        val result = restTemplate!!.getForEntity("http://localhost:$port/report?currency=EUR", String::class.java)
        assertTrue(!result.body.contains("APPROVED")) // resttemplate doesn't store session, so token was missing
    }
}