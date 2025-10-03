package br.com.project.newmoodplus.data.remote

import br.com.project.newmoodplus.data.dto.requests.LoginRequest
import br.com.project.newmoodplus.data.dto.requests.UserRequest
import br.com.project.newmoodplus.data.dto.responses.LoginResponse
import br.com.project.newmoodplus.data.dto.responses.UserResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


interface UserAPI {
    @GET("User")
    suspend fun getUser(): List<UserResponse>

    @POST("User")
    suspend fun postUser(@Body user: UserRequest): UserResponse

    @PUT("User")
    suspend fun putUser(): UserRequest

    @DELETE("User")
    suspend fun deleteUser(): UserRequest

    @POST("usuario/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse


}