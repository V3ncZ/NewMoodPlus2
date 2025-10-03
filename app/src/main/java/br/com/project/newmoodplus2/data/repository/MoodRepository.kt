package br.com.project.newmoodplus.data.repository

import android.content.Context
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.dto.responses.DailyMoodResponse
import br.com.project.newmoodplus.data.remote.MoodAPI
import br.com.project.newmoodplus.data.remote.datastore.RetrofitInstance
import br.com.project.newmoodplus.data.remote.datastore.SessionManager

class MoodRepository(private val context: Context) {

    private val api: MoodAPI = RetrofitInstance.getMoodApi(context)

    /**
     * Registra o humor do usuário
     */
    suspend fun registerMood(dailyMoodRequest: String, moodRequest: DailyMoodRequest): Boolean {
        return try {
            val token = SessionManager.getToken(context) ?: return false
            val response = api.registerMood("Bearer $token", dailyMoodRequest.toString())
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Retorna os moods do usuário logado
     */
    suspend fun getUserMoods(): List<DailyMoodResponse>? {
        return try {
            val token = SessionManager.getToken(context) ?: return null
            val response = api.getUserMoods("Bearer $token")
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
