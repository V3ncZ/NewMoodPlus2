package br.com.project.newmoodplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.com.project.newmoodplus.ui.viewmodel.LoginState
import br.com.project.newmoodplus2.R
import br.com.project.newmoodplus2.data.repository.UserRepository
import br.com.project.newmoodplus.ui.viewmodel.LoginViewModel
import br.com.project.newmoodplus.ui.viewmodel.factory.LoginScreenViewModelFactory
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("teste@teste.com") }
    var password by remember { mutableStateOf("123") }

    val context = LocalContext.current
    // A Factory agora precisa do context para criar o repositório
    val loginViewModel: LoginViewModel = viewModel(factory = LoginScreenViewModelFactory(UserRepository(context)))
    val loginState by loginViewModel.loginState.collectAsState()

    // Variáveis para controlar o Snackbar (mensagem de erro)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Reage às mudanças de estado do ViewModel
    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is LoginState.Success -> {
                navController.navigate("HomeScreen")
            }
            is LoginState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(state.message)
                }
            }
            else -> {} // Idle ou Loading
        }
    }

    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.lightBlue))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Um padding geral é bom
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ... seu código de Image e TextFields ...

            Button(
                // Desabilita o botão enquanto estiver carregando
                enabled = loginState != LoginState.Loading,
                onClick = { loginViewModel.login(email, password) },
                // ... resto do seu modificador e cores ...
            ) {
                // Mostra um indicador de progresso durante o login
                if (loginState == LoginState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text(text = "Entrar", /*...*/)
                }
            }
        }

        // Local para o Snackbar aparecer
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavHostController(LocalContext.current))
}