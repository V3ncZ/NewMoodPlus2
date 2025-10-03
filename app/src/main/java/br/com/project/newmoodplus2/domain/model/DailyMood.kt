package br.com.project.newmoodplus.domain.model

import java.util.Date

data class DailyMood(
    val day: Date,
    val mood: String,
    val influence: String,
    val sleep: String,
    val leadrshipRelation: String,
    val workRelation: String
)
