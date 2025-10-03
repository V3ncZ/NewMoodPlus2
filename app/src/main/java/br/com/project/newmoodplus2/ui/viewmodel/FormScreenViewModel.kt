import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.repository.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormScreenViewModel(private val repository: MoodRepository) : ViewModel() {

    private val _moodSaved = MutableStateFlow<Boolean?>(null)
    val moodSaved: StateFlow<Boolean?> = _moodSaved

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun saveMood(moodRequest: DailyMoodRequest) {
        viewModelScope.launch {
            try {
                val result = repository.registerMood("", moodRequest) // Token não é necessário aqui
                if (result) {
                    _moodSaved.value = true
                } else {
                    _error.value = "Erro ao salvar mood"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro desconhecido"
            }
        }
    }
}