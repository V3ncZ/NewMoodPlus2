package br.com.project.newmoodplus.domain.model

import androidx.compose.ui.semantics.Role

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val empresaId: String,
    val roles: List<Role>
)
