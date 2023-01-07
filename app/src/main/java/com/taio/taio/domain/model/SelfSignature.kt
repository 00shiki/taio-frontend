package com.taio.taio.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SelfSignature(
    val tergenerate: List<SignatureList> = emptyList(),
    val self: List<SignatureList> = tergenerate.filter{ it.self }
): Parcelable
