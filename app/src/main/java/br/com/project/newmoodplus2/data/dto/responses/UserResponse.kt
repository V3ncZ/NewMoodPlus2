package br.com.project.newmoodplus.data.dto.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_id") val id: String,
    val name: String,
    val email: String
)
