package org.sample.homework.domain

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
class TokenStatus(var value: String = "", var validTo: LocalDateTime = LocalDateTime.now())
