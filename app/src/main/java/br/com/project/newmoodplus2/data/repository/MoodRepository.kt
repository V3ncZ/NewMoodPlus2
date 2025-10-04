    package br.com.project.newmoodplus.data.repository

    import android.content.Context
    import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
    import br.com.project.newmoodplus.data.remote.MoodAPI
    import br.com.project.newmoodplus.data.remote.datastore.RetrofitInstance
    import br.com.project.newmoodplus.data.remote.datastore.SessionManager

    class MoodRepository(private val context: Context) {

        private val api: MoodAPI = RetrofitInstance.getMoodApi(context)

        private fun getToken(): String? {
            val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            val token = sharedPref.getString("TOKEN_KEY", null)
            return if (token != null) "Bearer $token" else null
        }

        suspend fun registerMood(moodRequest: DailyMoodRequest): Boolean {
            return try {
                val token = SessionManager.getToken(context) ?: return false
                // This call correctly uses SessionManager
                val response = api.registerMood("Bearer $token", moodRequest)
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

    }

