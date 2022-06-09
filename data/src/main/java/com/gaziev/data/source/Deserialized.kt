package com.gaziev.data.source

interface Deserialized <T, V> {
    fun get(data: T): V
}