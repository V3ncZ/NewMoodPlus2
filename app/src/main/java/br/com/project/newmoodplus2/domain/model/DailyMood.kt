package br.com.project.newmoodplus.domain.model

import java.util.Date

data class DailyMood(
    val dia: Date,
    val humor: String,
    val sentimento: String,
    val influencia: String,
    val sono: String,
    val relacaoLideranca: String,
    val impactoTrabalho: String
)
