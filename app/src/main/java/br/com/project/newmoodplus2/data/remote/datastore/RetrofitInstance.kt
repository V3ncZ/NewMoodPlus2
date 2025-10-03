package br.com.project.newmoodplus.data.remote.datastore

import android.content.Context
import br.com.project.newmoodplus.data.remote.CompaniesAPI
import br.com.project.newmoodplus.data.remote.EventsAPI
import br.com.project.newmoodplus.data.remote.MoodAPI
import br.com.project.newmoodplus.data.remote.UserAPI
import br.com.project.newmoodplus.domain.model.DailyMood
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:7190/api/v1.1/"

    fun getRetrofit(context: Context): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(context)) // 🔑 JWT entra aqui
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getUserApi(context: Context): UserAPI {
        return getRetrofit(context).create(UserAPI::class.java)
    }

    fun getMoodApi(context: Context): MoodAPI {
        return getRetrofit(context).create(MoodAPI::class.java)
    }


    fun getEventsApi(context: Context): EventsAPI {
        return getRetrofit(context).create(EventsAPI::class.java)
    }

    fun getCompaniesApi(context: Context): CompaniesAPI {
        return getRetrofit(context).create(CompaniesAPI::class.java)
    }

    fun getDailyMood(context: Context): DailyMood {
        return getRetrofit(context).create(DailyMood::class.java)
    }

}