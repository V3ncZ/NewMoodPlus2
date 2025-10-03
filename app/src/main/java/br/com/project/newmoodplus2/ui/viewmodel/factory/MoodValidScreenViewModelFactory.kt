package br.com.project.newmoodplus.ui.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.newmoodplus.ui.viewmodel.MoodValidScreenViewModel

class MoodValidScreenViewModelFactory(
    private val repository: Context,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodValidScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoodValidScreenViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
