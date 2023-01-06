package com.taio.taio.data.repository

import com.taio.taio.data.model.LoginRequest
import com.taio.taio.data.model.RegisterRequest
import com.taio.taio.data.model.SelfSignatureRequest
import com.taio.taio.data.model.SignatureMetadata
import com.taio.taio.data.network.ApiService
import com.taio.taio.domain.repository.ApiRepo
import retrofit2.Call
import okhttp3.ResponseBody
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(private val apiService: ApiService) : ApiRepo {
    override suspend fun login(email: String, password: String): Call<ResponseBody> {
        val loginRequest = LoginRequest(email, password)
        return apiService.login(loginRequest)
    }

    override suspend fun register(
        name: String,
        email: String,
        userName: String,
        phoneNumber: String,
        password: String
    ): Call<ResponseBody> {
        val registrationRequest = RegisterRequest(name, email, userName, phoneNumber, password)
        return apiService.register(registrationRequest)
    }

    override suspend fun getUserByUserName(userName: String): Call<ResponseBody> {
        return apiService.getUserByUserName(userName)
    }

    override suspend fun generateSelfSignature(
        title: String,
        date: String,
        documentNumber: String,
        description: String,
        status: Int,
        // file: Document, not yet implemented
        signatureMetadata: SignatureMetadata
    ): Call<ResponseBody> {
        val selfSignatureRequest = SelfSignatureRequest(
            title,
            date,
            documentNumber,
            description,
            status,
            // file,
            signatureMetadata
        )
        return apiService.generateSelfSignature(selfSignatureRequest)
    }


}