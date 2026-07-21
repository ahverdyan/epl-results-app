package com.example.futapp.model

import java.time.LocalDateTime

data class Match(
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val dateTime: LocalDateTime,
    val location: String,
    val homeScore: Int?,
    val awayScore: Int?
)