package br.com.project.newmoodplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus2.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// REMOVEMOS COMPLETAMENTE O CONTEXT DAQUI!
class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        // Informa a UI que o login está em andamento
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            val success = repository.login(email, password)
            if (success) {
                // O próprio repositório já salvou o token.
                _loginState.value = LoginState.Success
            } else {
                // Informa a UI que o login falhou
                _loginState.value = LoginState.Error("E-mail ou senha inválidos.")
            }
        }
    }
}

// Usar uma "sealed class" para representar os estados da UI é uma prática recomendada
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}