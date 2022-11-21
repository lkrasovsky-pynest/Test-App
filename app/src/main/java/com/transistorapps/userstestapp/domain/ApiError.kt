package com.transistorapps.userstestapp.domain

import com.transistorapps.userstestapp.R


sealed class ApiError {
    object Generic : ApiError()
    object NoInternetConnection : ApiError()

    fun message(): Int {
        return when (this) {
            Generic -> R.string.genericErrorMessage
            NoInternetConnection -> R.string.connectionErrorMessage
        }
    }
}