package br.com.project.newmoodplus.data.dto.responses

import br.com.project.newmoodplus.domain.model.Roles

data class LoginResponse(
    val token: String,
    val role: String
) {
}