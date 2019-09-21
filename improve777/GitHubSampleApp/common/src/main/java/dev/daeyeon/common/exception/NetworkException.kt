package dev.daeyeon.common.exception

class NetworkException(
    message: String,
    val code: Int
): Throwable(message)