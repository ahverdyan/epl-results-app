package com.example.futapp.side.dto

import com.example.futapp.model.Match
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class MatchDto(
    @SerializedName("HomeTeam")
    val homeTeam: String,

    @SerializedName("AwayTeam")
    val awayTeam: String,

    @SerializedName("DateUtc")
    val dateUtc: String,

    @SerializedName("Location")
    val location: String,

    @SerializedName("HomeTeamScore")
    val homeTeamScore: Int?,

    @SerializedName("AwayTeamScore")
    val awayTeamScore: Int?
) {
    fun toDomain(): Match {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'Z'")
        val localDateTime = LocalDateTime.parse(dateUtc, formatter)

        val utcDateTime = localDateTime.atZone(ZoneId.of("UTC"))
        val localDateTimeDevice = utcDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()

        return Match(
            id = "${homeTeam}_${awayTeam}_${dateUtc}",
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            dateTime = localDateTimeDevice,
            location = location,
            homeScore = homeTeamScore,
            awayScore = awayTeamScore
        )
    }
}