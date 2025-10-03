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
    @GET("usuario")
    suspend fun getUser(): List<UserResponse>

    @POST("usuario")
    suspend fun postUser(@Body user: UserRequest): UserResponse

    @PUT("usuario")
    suspend fun putUser(): UserRequest

    @DELETE("usuario")
    suspend fun deleteUser(): UserRequest

    @POST("usuario/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse


}