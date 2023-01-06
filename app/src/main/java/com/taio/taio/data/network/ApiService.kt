package com.taio.taio.data.network

import com.taio.taio.data.model.LoginRequest
import com.taio.taio.data.model.RegisterRequest
import com.taio.taio.data.model.SelfSignatureRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Call<ResponseBody>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Call<ResponseBody>

    @GET("{userName}")
    suspend fun getUserByUserName(@Path("userName") userName: String): Call<ResponseBody>

    @POST("/generate/live")
    suspend fun generateSelfSignature(@Body selfSignatureRequest: SelfSignatureRequest): Call<ResponseBody>
}