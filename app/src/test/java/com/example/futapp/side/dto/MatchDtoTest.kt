package com.example.futapp.side.dto

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.format.DateTimeFormatter
import kotlin.text.format

class MatchDtoTest {

    @Test
    fun `toDomain should parse date correctly`() {
        val dto = MatchDto(
            homeTeam = "Arsenal",
            awayTeam = "Chelsea",
            dateUtc = "2023-08-11 19:00:00Z",
            location = "Emirates Stadium",
            homeTeamScore = 2,
            awayTeamScore = 1
        )

        val match = dto.toDomain()

        assertEquals("Arsenal", match.homeTeam)
        assertEquals("Chelsea", match.awayTeam)
        assertEquals(2, match.homeScore)
        assertEquals(1, match.awayScore)
        assertEquals("Emirates Stadium", match.location)

        // Проверяем формат даты
        val formatted = match.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        assertEquals("2023-08-11", formatted)
    }

    @Test
    fun `toDomain should handle null scores`() {
        val dto = MatchDto(
            homeTeam = "Liverpool",
            awayTeam = "Man City",
            dateUtc = "2023-08-13 17:30:00Z",
            location = "Anfield",
            homeTeamScore = null,
            awayTeamScore = null
        )

        val match = dto.toDomain()

        assertNull(match.homeScore)
        assertNull(match.awayScore)
    }

    @Test
    fun `toDomain should generate unique id`() {
        val dto = MatchDto(
            homeTeam = "Arsenal",
            awayTeam = "Chelsea",
            dateUtc = "2023-08-11 19:00:00Z",
            location = "Emirates",
            homeTeamScore = 0,
            awayTeamScore = 0
        )

        val match = dto.toDomain()

        assertNotNull(match.id)
        assertEquals("Arsenal_Chelsea_2023-08-11 19:00:00Z", match.id)
    }
}