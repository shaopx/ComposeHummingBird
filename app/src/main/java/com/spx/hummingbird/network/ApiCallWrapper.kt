package com.spx.hummingbird.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.IOException


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}


suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        println("shaopx debug exception:${throwable.toString()}")
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                ResultWrapper.GenericError(code, errorResponse)
            }

            else -> {
                ResultWrapper.GenericError(null, null)
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.source()?.toString()
    } catch (exception: Exception) {
        null
    }
}

fun createRequestBody(customParams: Map<String, Any>): RequestBody {
    val params = mutableMapOf<String, Any?>(
        "pkg" to "com.fn.android",
        "os" to "Android",
        "oversion" to "12",
        "dmake" to "Xiaomi",
        "dmodel" to "Xiaomi",
        "aversion" to "5.2.1",
        "deviceid" to "umi",
        "isIos" to "false",
        "carrier" to "0",
        "project" to "fnSmallProgram"
    )
    params.putAll(customParams)
    val encodedParams =
        params.entries.joinToString("&") { "${it.key}=${it.value}" }
    return encodedParams.toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())
}