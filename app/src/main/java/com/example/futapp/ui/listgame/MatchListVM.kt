package com.example.futapp.ui.listgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futapp.side.api.EplApiService
import com.example.futapp.side.rep.MatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<MatchListState>(MatchListState.Loading)
    val uiState: StateFlow<MatchListState> = _uiState

    private val api: EplApiService = Retrofit.Builder()
        .baseUrl("https://fixturedownload.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EplApiService::class.java)

    private val repository = MatchRepository(api)

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            _uiState.value = MatchListState.Loading
            try {
                val matches = repository.getMatches()
                _uiState.value = MatchListState.Success(matches)
            } catch (e: Exception) {
                _uiState.value = MatchListState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }
}