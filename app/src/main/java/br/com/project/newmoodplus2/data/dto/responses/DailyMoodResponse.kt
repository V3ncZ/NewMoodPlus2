package br.com.project.newmoodplus.data.dto.responses

import java.util.Date

data class DailyMoodResponse(
    val dia: Date,
    val humor: String,
    val sentimento: String,
    val influencia: String,
    val sono: String,
    val relacaoLideranca: String,
    val relacaoTrabalho: String
)
