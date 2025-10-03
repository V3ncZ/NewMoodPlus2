package br.com.project.newmoodplus.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.project.newmoodplus.ui.viewmodel.MoodRegistrationState
import br.com.project.newmoodplus.ui.viewmodel.MoodValidScreenViewModel
import br.com.project.newmoodplus.ui.viewmodel.factory.MoodValidScreenViewModelFactory
import br.com.project.newmoodplus2.R // Verifique se este é o caminho correto para sua classe R

@Composable
fun MoodValidScreen(
    navController: NavController
) {
    val context = LocalContext.current

    // A Factory é criada passando apenas o context para instanciar o ViewModel corretamente
    val moodValidViewModel: MoodValidScreenViewModel = viewModel(
        factory = MoodValidScreenViewModelFactory(
            context,
            context = TODO()
        )
    )
    val moodState by moodValidViewModel.moodState.collectAsState()

    // Efeito que reage às mudanças de estado para navegar ou mostrar erro
    LaunchedEffect(moodState) {
        when (val state = moodState) {
            is MoodRegistrationState.Success -> {
                // Navega para a tela do formulário, passando o humor como argumento
                navController.navigate("formScreen/${state.mood}")
            }
            is MoodRegistrationState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            else -> {} // Não faz nada em Idle ou Loading
        }
    }

    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.lightBlue)) // Corrigido
            .fillMaxSize()
            .padding(16.dp), // Um padding geral para a tela
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Nos conte como você está hoje",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.Dark),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Variável para controlar se os botões devem estar ativos
                val isLoading = moodState is MoodRegistrationState.Loading

                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clickable(enabled = !isLoading) { // Desabilita o clique durante o loading
                            moodValidViewModel.registerMood("happy")
                        },
                    painter = painterResource(id = R.drawable.happy),
                    contentDescription = "Happy Face"
                )

                Image(
                    modifier = Modifier
                        .size(75.dp)
                        .clickable(enabled = !isLoading) {
                            moodValidViewModel.registerMood("neutral")
                        },
                    painter = painterResource(id = R.drawable.neutral),
                    contentDescription = "Neutral Face"
                )

                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clickable(enabled = !isLoading) {
                            moodValidViewModel.registerMood("sad")
                        },
                    painter = painterResource(id = R.drawable.sad),
                    contentDescription = "Sad Face"
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoodValidScreenPreview() {
    // O NavController do preview não executa a navegação, serve apenas para não dar erro.
    MoodValidScreen(navController = rememberNavController())
}