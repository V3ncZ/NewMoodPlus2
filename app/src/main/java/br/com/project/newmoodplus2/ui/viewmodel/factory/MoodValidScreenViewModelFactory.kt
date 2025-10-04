// File: MoodValidScreenViewModelFactory.kt

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.newmoodplus.data.repository.MoodRepository // Make sure this import is present
import br.com.project.newmoodplus.ui.viewmodel.MoodValidScreenViewModel

class MoodValidScreenViewModelFactory(
    // 1. The factory should receive the Context.
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodValidScreenViewModel::class.java)) {
            // 2. Use the context to create an instance of MoodRepository.
            val repository = MoodRepository(context.applicationContext)

            @Suppress("UNCHECKED_CAST")
            // 3. Pass the newly created repository to the ViewModel.
            return MoodValidScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}