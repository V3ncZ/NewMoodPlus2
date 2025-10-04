package br.com.project.newmoodplus.data.remote

import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.dto.responses.DailyMoodResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MoodAPI {

    @POST("mood")
    suspend fun registerMood(
        @Header("Authorization") token: String,
        @Body moodRequest: DailyMoodRequest
    ): Response<Void>

    @GET("mood")
    suspend fun getUserMoods(
        @Header("Authorization") token: String
    ): Response<List<DailyMoodResponse>>
}
