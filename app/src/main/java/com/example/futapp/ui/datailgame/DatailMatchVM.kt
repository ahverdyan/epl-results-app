package com.example.futapp.ui.datailgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futapp.side.api.EplApiService
import com.example.futapp.side.rep.MatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchDetailVM : ViewModel() {

    private val _uiState = MutableStateFlow<DatailMatchState>(DatailMatchState.Loading)
    val uiState: StateFlow<DatailMatchState> = _uiState

    private val api: EplApiService = Retrofit.Builder()
        .baseUrl("https://fixturedownload.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EplApiService::class.java)

    private val repository = MatchRepository(api)

    fun loadMatch(matchId: String) {
        viewModelScope.launch {
            _uiState.value = DatailMatchState.Loading
            try {
                val match = repository.getMatchById(matchId)
                if (match != null) {
                    _uiState.value = DatailMatchState.Success(match)
                } else {
                    _uiState.value = DatailMatchState.Error("Матч не найден")
                }
            } catch (e: Exception) {
                _uiState.value = DatailMatchState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }
}