package br.com.project.newmoodplus.domain.model

data class Mood(
    val id: String,
    val userId: String,
    val companyId: String,
    val dailyMoods: List<DailyMood>
)
