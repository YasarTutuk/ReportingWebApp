package org.sample.homework.service

interface Serializer {
    fun <T> serialize(obj: T) : String
    fun <T> deserialize(str: String, type: Class<T>) : T
}