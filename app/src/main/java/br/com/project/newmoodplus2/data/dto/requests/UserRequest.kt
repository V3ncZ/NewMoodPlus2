package br.com.project.newmoodplus.data.dto.requests

import com.google.gson.annotations.SerializedName

data class UserRequest(
    val name: String,
    val email: String,
    val password: String
)
