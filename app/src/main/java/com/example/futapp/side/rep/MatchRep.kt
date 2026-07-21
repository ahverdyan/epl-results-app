package com.example.futapp.side.rep

import com.example.futapp.side.api.EplApiService
import com.example.futapp.model.Match

class MatchRepository(
    private val api: EplApiService
) {
    suspend fun getMatches(): List<Match> {
        val dtos = api.getMatches()
        return dtos.map { it.toDomain() }
    }

    suspend fun getMatchById(matchId: String): Match? {
        val matches = getMatches()
        return matches.find { it.id == matchId }
    }
}