package br.com.project.newmoodplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.newmoodplus2.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginSuccess = MutableStateFlow<Boolean?>(null) // Valor inicial nulo
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            _loginSuccess.value = result
        }
    }

    fun getUserRole(): String? = repository.getUserRole()
}