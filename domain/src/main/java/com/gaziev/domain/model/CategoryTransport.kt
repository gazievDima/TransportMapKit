package com.gaziev.domain.model

sealed class CategoryTransport {
    abstract val root: String
    abstract val name: String

    class Private(
        override val root: String = "private",
        override val name: String = "Личные авто"
    ) : CategoryTransport()

    class Shared(
        override val root: String = "shared",
        override val name: String = "Общие авто"
    ) : CategoryTransport()
}