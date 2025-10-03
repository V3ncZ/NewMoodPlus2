package br.com.project.newmoodplus.data.remote

import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.dto.responses.MoodResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MoodAPI {

    @GET("Mood")
    suspend fun getMood(): List<MoodResponse>

    @POST("Mood")
    suspend fun postMood(@Body mood: DailyMoodRequest): MoodResponse
}