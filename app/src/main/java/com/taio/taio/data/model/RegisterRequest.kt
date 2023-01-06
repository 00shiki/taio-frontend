package com.taio.taio.data.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val userName: String,
    val phoneNumber: String,
    val password: String
)
