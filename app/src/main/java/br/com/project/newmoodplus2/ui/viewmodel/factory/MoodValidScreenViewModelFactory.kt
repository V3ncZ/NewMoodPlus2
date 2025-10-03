package br.com.project.newmoodplus2.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.newmoodplus.data.repository.MoodRepository
import br.com.project.newmoodplus.ui.viewmodel.MoodValidScreenViewModel
import kotlin.jvm.java

class MoodValidScreenViewModelFactory(
    private val repository: MoodRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodValidScreenViewModel::class.java)) {
            return MoodValidScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}