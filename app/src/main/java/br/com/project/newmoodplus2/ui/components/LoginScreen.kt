package br.com.project.newmoodplus.ui.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel // <-- CORREÇÃO 2: IMPORT ADICIONADO
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.com.project.newmoodplus2.R
import br.com.project.newmoodplus2.data.repository.UserRepository
import br.com.project.newmoodplus.ui.viewmodel.LoginViewModel
import br.com.project.newmoodplus.ui.viewmodel.LoginViewModelFactory


@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val userRepository = remember { UserRepository(context.applicationContext) }
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(userRepository))
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            navController.navigate("HomeScreen")
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
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            TextField(
                value = email,
                onValueChange = { email = it }, // Simplificado
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.padding(10.dp))

            TextField(
                value = password,
                onValueChange = { password = it }, // Simplificado
                label = { Text("Password") }
            )

            Button(
                onClick = { loginViewModel.login(email, password) },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(220.dp, 90.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Yellow)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Entrar",
                    color = colorResource(id = R.color.Dark),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    // CORREÇÃO 3: REMOVIDO O PARÂMETRO EXTRA 'CONTEXT'
    LoginScreen(navController = NavHostController(LocalContext.current))
}