package com.taio.taio.data

import android.graphics.Bitmap

data class CreateState(
    val documentName: String = "",
    val documentDate: String = "",
    val documentNumber: String = "",
    val documentDescription: String = "",
    val isFormError: Boolean = false,
    val signature: Bitmap? = null
)
