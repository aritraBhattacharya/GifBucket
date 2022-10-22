package com.aritra.gifbucket.data.utils

import com.aritra.gifbucket.R
import retrofit2.Response

abstract class SafeAPIRequest {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun <T : Any> apiRequest(call: suspend (() -> Response<T>)): Resource<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let responseHandler.handleSuccess(it)
                }
                    ?: responseHandler.handleError("common error message")
            } else {
                responseHandler.handleError(
                    "default error message"
                )
            }
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}