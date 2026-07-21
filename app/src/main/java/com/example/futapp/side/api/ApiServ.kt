package com.example.futapp.side.api

import com.example.futapp.side.dto.MatchDto
import retrofit2.http.GET

interface EplApiService {
    @GET("feed/json/epl-2023")
    suspend fun getMatches(): List<MatchDto>
}