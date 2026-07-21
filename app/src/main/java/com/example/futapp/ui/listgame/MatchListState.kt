package com.example.futapp.ui.listgame

import com.example.futapp.model.Match

sealed class MatchListState {
    object Loading : MatchListState()
    data class Success(val matches: List<Match>) : MatchListState()
    data class Error(val message: String) : MatchListState()
}