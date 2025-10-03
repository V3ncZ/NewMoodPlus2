package br.com.project.newmoodplus.data.repository

import android.content.Context
import android.util.Base64
import br.com.project.newmoodplus.data.dto.requests.LoginRequest
import br.com.project.newmoodplus.data.dto.requests.UserRequest
import br.com.project.newmoodplus.data.dto.responses.UserResponse
import br.com.project.newmoodplus.data.remote.UserAPI
import br.com.project.newmoodplus.data.remote.datastore.RetrofitInstance
import br.com.project.newmoodplus.data.remote.datastore.SessionManager
import org.json.JSONObject


class UserRepository(private val context: Context) {

    private val api: UserAPI = RetrofitInstance.getUserApi(context)

    fun extractRoleFromToken(token: String): String? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null
            val payload = String(Base64.decode(parts[1], Base64.URL_SAFE))
            val json = JSONObject(payload)
            json.getString("http://schemas.microsoft.com/ws/2008/06/identity/claims/role")
        } catch (e: Exception) {
            null
        }
    }

    suspend fun login(email: String, password: String): Boolean{
        return try {
            val response = api.login(LoginRequest(email, password))

            val role = extractRoleFromToken(response.token) ?: ""

            // Salva token e role no SharedPreferences
            SessionManager.saveAuthToken(context, response.token, role)

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Retorna o role do usuário logado
     */
    fun getUserRole(): String? {
        return SessionManager.getRole(context)
    }

    /**
     * Retorna o token JWT do usuário logado
     */
    fun getToken(): String? {
        return SessionManager.getToken(context)
    }
}
