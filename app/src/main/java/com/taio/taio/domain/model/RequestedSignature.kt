package com.taio.taio.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestedSignature(
    val tergenerate: List<SignatureList> = emptyList(),
    val requested: List<SignatureList> = tergenerate.filter{ it.requested }
): Parcelable
