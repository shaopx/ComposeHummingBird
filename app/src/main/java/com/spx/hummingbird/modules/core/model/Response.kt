package com.spx.hummingbird.modules.core.model

data class Response<T>(
    val code: String,
    val msg: String,
    val expires: Long,
    val data: T
)
