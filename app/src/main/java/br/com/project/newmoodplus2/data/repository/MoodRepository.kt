package br.com.project.newmoodplus.data.repository

import android.content.Context
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.remote.MoodAPI // Supondo que você tenha isso
import br.com.project.newmoodplus.data.remote.datastore.RetrofitInstance

class MoodRepository(private val context: Context) {

    private val api: MoodAPI = RetrofitInstance.getMoodApi(context) // Sua instância do Retrofit

    // O SharedPreferences é acessado aqui dentro
    private fun getToken(): String? {
        val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN_KEY", null)
        return if (token != null) "Bearer $token" else null
    }

    // A função não precisa mais receber o token como parâmetro
    suspend fun registerMood(moodRequest: String, moodRequest1: DailyMoodRequest): Boolean {
        val token = getToken()
        if (token == null) {
            // Se não houver token, a operação falha imediatamente
            return false
        }

        return try {
            // val response = api.registerMood(token, moodRequest)
            // response.isSuccessful

            // Simulação para teste
            true
        } catch (e: Exception) {
            false
        }
    }
}