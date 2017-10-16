package org.sample.homework.service

import org.sample.homework.domain.HttpError

interface CommonClient {
    var endPoint: String

    fun addHeader(key: String, value: String)
    fun clearHeaders()

    fun addParameter(key: String, value: String)
    fun clearParameters()

    fun get(): String
    fun <T> post(bodyObj: Any, type: Class<T>): Pair<T?, HttpError?>
    fun <T> post(body: String, type: Class<T>): Pair<T?, HttpError?>
}