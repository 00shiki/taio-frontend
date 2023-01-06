package com.taio.taio.data.model

data class SignatureMetadata(
    val city: String,
    val state: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val ipv4: String,

    val device_brand: String,
)
