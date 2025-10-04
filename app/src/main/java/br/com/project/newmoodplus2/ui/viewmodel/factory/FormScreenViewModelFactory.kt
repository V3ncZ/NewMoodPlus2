package br.com.project.newmoodplus2.ui.viewmodel.factory

import FormScreenViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.newmoodplus.data.repository.MoodRepository

class FormScreenViewModelFactory(
    private val repository: MoodRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormScreenViewModel::class.java)) {
            return FormScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
