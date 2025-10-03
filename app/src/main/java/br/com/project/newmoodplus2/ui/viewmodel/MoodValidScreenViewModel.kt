package br.com.project.newmoodplus.ui.viewmodel

import MoodRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus.data.dto.responses.DailyMoodResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoodValidScreenViewModel(private val repository: MoodRepository) : ViewModel() {

    private val _moods = MutableStateFlow<List<DailyMoodResponse>?>(null)
    val moods: StateFlow<List<DailyMoodResponse>?> = _moods

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchUserMoods(token: String) {
        viewModelScope.launch {
            val result = repository.getUserMoods()
            if (result != null) {
                _moods.value = result
            } else {
                _error.value = "Erro ao carregar moods"
            }
        }
    }
}

