package org.sample.homework.service

import com.google.gson.GsonBuilder
import org.springframework.stereotype.Service

@Service
class GsonSerializer : Serializer {
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun <T> serialize(obj: T): String {
        return gson.toJson(obj)
    }

    override fun <T> deserialize(str: String, type: Class<T>): T {
        return gson.fromJson(str, type)
    }
}