package com.taio.taio.domain.repository

import com.taio.taio.data.model.SignatureMetadata
import com.taio.taio.domain.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File

interface ApiRepo {
    suspend fun login(email: String, password: String): Call<ResponseBody>
    suspend fun register(
        name: String,
        email: String,
        userName: String,
        phoneNumber: String,
        password: String
    ): Call<ResponseBody>
    suspend fun getUserByUserName(userName: String): Call<ResponseBody>
    suspend fun generateSelfSignature(
        title: String,
        date: String,
        documentNumber: String,
        description: String,
        status: Int,
        // file: Document, not yet implemented
        signatureMetadata: SignatureMetadata
    ): Call<ResponseBody>
}