package com.angelatest.data.remote.api

import com.angelatest.data.model.home.medicineResponse.Problems
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3ab65141-7331-40ab-ac45-092d494a5570")
    suspend fun getProblems(): Response<Problems>
}