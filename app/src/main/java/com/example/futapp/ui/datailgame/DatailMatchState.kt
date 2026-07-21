package com.example.futapp.ui.datailgame

import com.example.futapp.model.Match

sealed class DatailMatchState {
    object Loading : DatailMatchState()
    data class Success(val match: Match) : DatailMatchState()
    data class Error(val message: String) : DatailMatchState()
}