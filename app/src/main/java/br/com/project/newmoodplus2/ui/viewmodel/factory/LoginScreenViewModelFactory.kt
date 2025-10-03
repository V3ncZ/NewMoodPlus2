package br.com.project.newmoodplus.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.newmoodplus.ui.viewmodel.LoginViewModel
import br.com.project.newmoodplus2.data.repository.UserRepository // Verifique se o import do repositório está correto

class LoginScreenViewModelFactory(
    private val context: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica se a classe do ViewModel que está sendo pedida é a LoginViewModel
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

            // 1. A Factory cria a dependência (UserRepository)
            val repository = UserRepository(context.applicationContext)

            // 2. A Factory cria e retorna o LoginViewModel, passando a dependência para ele
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }

        // Lança um erro se um ViewModel desconhecido for solicitado
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}