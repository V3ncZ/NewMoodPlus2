package br.com.project.newmoodplus.ui.components

import FormScreenViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.project.newmoodplus2.R
import br.com.project.newmoodplus.data.dto.requests.DailyMoodRequest
import br.com.project.newmoodplus.data.repository.MoodRepository
import br.com.project.newmoodplus.domain.model.Pergunta
import br.com.project.newmoodplus2.ui.viewmodel.factory.FormScreenViewModelFactory
import kotlin.collections.set

@Composable
fun FormScreen(navController: NavController) {
    val context = LocalContext.current

    // Instancia Repository e ViewModel
    val moodRepository = remember { MoodRepository(context) }
    val formViewModel: FormScreenViewModel = viewModel(
        factory = FormScreenViewModelFactory(moodRepository)
    )

    val moodSaved by formViewModel.moodSaved.collectAsState()
    val error by formViewModel.error.collectAsState()

    val perguntas = listOf(
        Pergunta("Como você está se sentindo hoje?", listOf("Motivado", "Cansado", "Preocupado", "Estressado", "Satisfeito", "Animado")),
        Pergunta("O que influenciou seu humor?", listOf("Trabalho", "Família", "Saúde", "Relacionamentos", "Estudos")),
        Pergunta("Como foi seu sono?", listOf("Muito bom", "Regular", "Ruim")),
        Pergunta("Como você avalia a relação com a liderança na empresa?", listOf("Muito boa", "Boa", "Mais ou menos", "Ruim", "Muito ruim")),
        Pergunta("Como você avalia o impacto do trabalho na sua vida pessoal?", listOf("Muito boa", "Boa", "Mais ou menos", "Ruim", "Muito ruim"))
    )

    var perguntaAtual by remember { mutableStateOf(0) }
    val respostasSelecionadas = remember { mutableStateMapOf<Int, String>() }

    val pergunta = perguntas[perguntaAtual]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lightBlue))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )

        Text(
            text = pergunta.texto,
            color = colorResource(id = R.color.Dark),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        var opcaoSelecionada by remember(perguntaAtual) { mutableStateOf("") }

        pergunta.opcoes.forEach { opcao ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = opcaoSelecionada == opcao,
                    onClick = { opcaoSelecionada = opcao }
                )
                Text(
                    text = opcao,
                    color = colorResource(id = R.color.Dark),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                respostasSelecionadas[perguntaAtual] = opcaoSelecionada

                if (perguntaAtual < perguntas.size - 1) {
                    perguntaAtual++
                } else {
                    // Cria DailyMoodRequest e envia via ViewModel
                    val moodRequest = DailyMoodRequest(
                        humor = respostasSelecionadas[0] ?: "",
                        sentimento = respostasSelecionadas[0] ?: "",
                        influencia = respostasSelecionadas[1] ?: "",
                        sono = respostasSelecionadas[2] ?: "",
                        relacaoLideranca = respostasSelecionadas[3] ?: "",
                        relacaoTrabalho = respostasSelecionadas[4] ?: ""
                    )
                    formViewModel.saveMood(moodRequest)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Yellow)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(220.dp, 64.dp)
                .padding(horizontal = 32.dp)
                .shadow(5.dp, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                text = if (perguntaAtual < perguntas.size - 1) "Próximo" else "Enviar",
                color = colorResource(id = R.color.Dark),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar status
        when {
            moodSaved == true -> {
                LaunchedEffect(Unit) {
                    navController.navigate("HomeScreen") {
                        popUpTo("FormScreen") { inclusive = true }
                    }
                }
            }
            error != null -> {
                Text(
                    text = error ?: "Erro desconhecido",
                    color = colorResource(id = R.color.Yellow),
                    fontWeight = FontWeight.Bold
                )
            }
            else -> {}
        }
    }
}
