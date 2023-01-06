package com.taio.taio.data.model

import com.google.gson.annotations.SerializedName

data class SelfSignatureRequest(
    val title: String,
    val date: String,
    @SerializedName("document_number") val documentNumber: String,
    val description: String,
    val status: Int,
    // val file: Document, not yet implemented
    @SerializedName("signature_metadata") val signatureMetadata: SignatureMetadata,
)
