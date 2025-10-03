package br.com.project.newmoodplus.domain.model

import java.util.Date

data class Events(
    val id: String,
    val companyId: String,
    val beginDate: Date,
    val endDate: Date,
    val title: String,
    val description: String
)