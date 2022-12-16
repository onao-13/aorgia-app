package com.example.aorgia.api.response

data class ApiResponse<T>(
    var status: Status,
    var code: Int,
    val body: T?
)
