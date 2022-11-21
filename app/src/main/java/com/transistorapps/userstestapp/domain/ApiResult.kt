package com.transistorapps.userstestapp.domain

sealed class ApiResult {
    object Success : ApiResult()
    data class Error(val error: ApiError) : ApiResult()
}

fun ApiError.toError() = ApiResult.Error(this)

