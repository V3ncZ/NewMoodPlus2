package br.com.project.newmoodplus.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.repository.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoodValidScreenViewModel(
    private val repository: MoodRepository
) : ViewModel() {

    private val _moodState = MutableStateFlow<MoodRegistrationState>(MoodRegistrationState.Idle)
    val moodState = _moodState.asStateFlow()

    fun registerMood(humor: String) {
        _moodState.value = MoodRegistrationState.Loading

//        fun buscarPorData(data: LocalDate): DailyMoodRequest? {
//            // Exemplo fictício — depois você adapta para o repositório real
//            return repository.getMoods().find { it.data == data.toString() }
//        }
//
//        fun setMoodModel(mood: DailyMoodRequest) {
//            // Aqui você pode armazenar o humor atual, se quiser manter em memória
//            _moodState.value = MoodRegistrationState.Success(mood.humor)
//        }


        viewModelScope.launch {
            try {
                val moodRequest = DailyMoodRequest(
                    humor = humor,
                    sentimento = "",
                    influencia = "",
                    sono = "",
                    relacaoLideranca = "",
                    relacaoTrabalho = ""
                )

                val success = repository.registerMood(moodRequest)

                if (success) {
                    _moodState.value = MoodRegistrationState.Success(humor)
                } else {
                    _moodState.value = MoodRegistrationState.Error("Não foi possível registrar o humor. Tente novamente.")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _moodState.value = MoodRegistrationState.Error("Ocorreu um erro inesperado.")
            }
        }
    }
}

sealed class MoodRegistrationState {
    object Idle : MoodRegistrationState()
    object Loading : MoodRegistrationState()
    data class Success(val mood: String) : MoodRegistrationState()
    data class Error(val message: String) : MoodRegistrationState()
}
