package com.example.aorgia.api.response

sealed class StatusCodeApi(
    val code: Int
) {
    object SUCCESS : StatusCodeApi(200)
    object NOT_FOUND : StatusCodeApi(404)
    object BAD_REQUEST : StatusCodeApi(400)
}
