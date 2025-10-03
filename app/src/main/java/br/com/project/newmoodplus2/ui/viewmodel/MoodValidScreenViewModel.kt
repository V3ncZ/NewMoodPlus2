package br.com.project.newmoodplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.dto.responses.DailyMoodResponse
import br.com.project.newmoodplus.data.repository.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoodValidScreenViewModel(private val repository: MoodRepository) : ViewModel() {

    private val _moods = MutableStateFlow<List<DailyMoodResponse>?>(null)
    val moods: StateFlow<List<DailyMoodResponse>?> = _moods

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    /**
     * Busca os moods do usuário logado
     */
    fun fetchUserMoods() {
        viewModelScope.launch {
            val result = repository.getUserMoods()
            if (result != null) {
                _moods.value = result
            } else {
                _error.value = "Erro ao carregar moods"
            }
        }
    }

    /**
     * Registra um novo mood do usuário
     */
    fun registerMood(humor: String) {
        viewModelScope.launch {
            val moodRequest = DailyMoodRequest(
                humor = humor,
                sentimento = "",
                influencia = "",
                sono = "",
                relacaoLideranca = "",
                relacaoTrabalho = ""
            )
            val success = repository.registerMood(moodRequest.toString(), moodRequest)
            if (!success) {
                _error.value = "Erro ao registrar humor"
            } else {
                fetchUserMoods()
            }
        }
    }
}
