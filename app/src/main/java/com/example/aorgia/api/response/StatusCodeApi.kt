package com.example.aorgia.api.response

sealed class StatusCodeApi(
    val code: Int
) {
    object SUCCESS : StatusCodeApi(200)
    object BAD_REQUEST : StatusCodeApi(400)
    object NOT_FOUND : StatusCodeApi(404)
    object CONFLICT : StatusCodeApi(409)
}
