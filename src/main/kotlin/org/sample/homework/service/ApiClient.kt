package org.sample.homework.service

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.sample.homework.domain.HttpError
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
class ApiClient constructor(private var uri: String = "https://sandbox-reporting.rpdpymnt.com/api/v3") : CommonClient {

    internal constructor(uri: String, serializer: Serializer) : this() {
        this.uri = uri
        this.serializer = serializer
    }

    @Autowired
    lateinit var serializer: Serializer

    private val client = HttpClients.createDefault()
    private val headers = mutableMapOf("content-type" to "application/json")
    private val parameters = mutableMapOf<String, String>()

    override var endPoint: String = ""

    override fun addHeader(key: String, value: String) {
        headers[key] = value
    }

    override fun clearHeaders() {
        headers.clear()
    }

    override fun addParameter(key: String, value: String) {
        parameters[key] = value
    }

    override fun clearParameters() {
        parameters.clear()
    }

    override fun get(): String {
        val builder = URIBuilder(buildEndpoint())
        //builder.addParameters(parameters.map{ BasicNameValuePair(it.key, it.value) })
        parameters.forEach { builder.addParameter(it.key, it.value) }

        val request = HttpGet(builder.build())
        headers.forEach({ request.addHeader(it.key, it.value) })

        val httpResponse = client.execute(request)
        val responseStr = BufferedReader(InputStreamReader(httpResponse.entity.content))
                .readText()

        httpResponse.use { return responseStr }
    }

    override fun <T> post(bodyObj: Any, type: Class<T>): Pair<T?, HttpError?> {
        val request = HttpPost(buildEndpoint())
        headers.forEach({ request.addHeader(it.key, it.value) })
        request.entity = StringEntity(serializer.serialize(bodyObj))

        return executePost(request, type)
    }

    override fun <T> post(body: String, type: Class<T>): Pair<T?, HttpError?> {
        val request = HttpPost(buildEndpoint())
        headers.forEach({ request.addHeader(it.key, it.value) })
        request.entity = StringEntity(body)

        return executePost(request, type)
    }

    private fun <T> executePost(request: HttpPost, type: Class<T>): Pair<T?, HttpError?> {
        val httpResponse = client.execute(request)

        httpResponse.use {
            val status = httpResponse.statusLine.statusCode
            val responseStr = BufferedReader(InputStreamReader(httpResponse.entity.content))
                    .readText()

            return if (status != 200) Pair(null, HttpError(status.toString(), responseStr))
            else Pair(serializer.deserialize(responseStr, type), null)
        }
    }

    private fun buildEndpoint(): String {
        return uri + endPoint
    }
}