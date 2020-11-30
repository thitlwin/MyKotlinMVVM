package com.twinrock.mymvvm.data.remote

import com.twinrock.mymvvm.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("user")
    suspend fun getAllUser() : Response<List<User>>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<User>

    @POST("user")
    suspend fun postUser(user: User): Response<User>
}