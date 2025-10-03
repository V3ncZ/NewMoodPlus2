package br.com.project.newmoodplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.repository.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// 1. REMOVEMOS O CONTEXT DO CONSTRUTOR
class MoodValidScreenViewModel(
    private val repository: MoodRepository
) : ViewModel() {

    // 2. Usamos uma Sealed Class para um controle de estado mais rico
    private val _moodState = MutableStateFlow<MoodRegistrationState>(MoodRegistrationState.Idle)
    val moodState = _moodState.asStateFlow()

    fun registerMood(humor: String) {
        // Informa a UI que uma operação está em andamento
        _moodState.value = MoodRegistrationState.Loading

        viewModelScope.launch {
            try {
                // Cria o objeto de requisição. Os outros campos serão preenchidos na próxima tela.
                val moodRequest = DailyMoodRequest(
                    humor = humor,
                    sentimento = "",
                    influencia = "",
                    sono = "",
                    relacaoLideranca = "",
                    relacaoTrabalho = ""
                )

                // 3. A responsabilidade de pegar o token agora é 100% do repositório
                val success = repository.registerMood(moodRequest.toString(), moodRequest)

                if (success) {
                    // Informa a UI que o registro foi um sucesso e passa o humor selecionado
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

// Sealed Class para representar os diferentes estados da tela
sealed class MoodRegistrationState {
    object Idle : MoodRegistrationState()
    object Loading : MoodRegistrationState()
    data class Success(val mood: String) : MoodRegistrationState()
    data class Error(val message: String) : MoodRegistrationState()
}