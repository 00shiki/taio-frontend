package com.taio.taio.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignatureList(
    val documentName: String,
    val documentDate: String,
    val documentNumber: String,
    val documentDescription: String,
    val self: Boolean = false,
    val requested: Boolean = false,
    val from: String = "",
    val to: String = "",
): Parcelable
