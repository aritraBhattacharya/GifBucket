package com.aritra.gifbucket.data.utils


import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    NetworkConnectionError(-2)
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T?): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleError(message: String): Resource<T> {
        return Resource.error(message, null)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code(), null), null)
            is SocketTimeoutException -> Resource.error(
                getErrorMessage(
                    ErrorCodes.SocketTimeOut.code,
                    null
                ), null
            )
            is IOException -> Resource.error(
                getErrorMessage(
                    ErrorCodes.NetworkConnectionError.code,
                    null
                ), null
            )
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE, null), null)
        }
    }

    fun <T : Any> handleHttpResponseError(
        response: Response<*>,
        defaultMessage: String?
    ): Resource<T> {
        return Resource.error(getErrorMessage(response.code(), defaultMessage), null)
    }


    // send error message based on error code
    private fun getErrorMessage(code: Int, defaultMessage: String?): String {
//        return when (code) {
//            ErrorCodes.SocketTimeOut.code, ErrorCodes.NetworkConnectionError.code -> EntradaApplication.getAppContext()
//                .getString(R.string.http_network_error_code)
//            GenericConstants.HTTP_BAD_REQUEST -> EntradaApplication.getAppContext().getString(R.string.http_400_error_code)
//            GenericConstants.HTTP_UNAUTHORIZED -> EntradaApplication.getAppContext().getString(R.string.http_401_error_code)
//            GenericConstants.HTTP_FORBIDDEN -> EntradaApplication.getAppContext().getString(R.string.http_403_error_code)
//            GenericConstants.HTTP_NOT_FOUND -> EntradaApplication.getAppContext().getString(R.string.http_404_error_code)
//            GenericConstants.HTTP_PRECONDITION_FAILED -> EntradaApplication.getAppContext().getString(R.string.http_412_error_code)
//            in 500..599 -> EntradaApplication.getAppContext().getString(R.string.http_503_error_code)
//            GenericConstants.HTTP_GATEWAY_TIMEOUT, GenericConstants.GENERIC_TIME_OUT -> EntradaApplication.getAppContext()
//                .getString(R.string.request_timeout)
//            else -> {
//                if (TextUtils.isEmpty(defaultMessage)) {
//                    EntradaApplication.getAppContext().getString(R.string.common_error_message_new)
//                } else {
//                    defaultMessage!!
//                }
//            }
//        }
        return defaultMessage!!
    }
}