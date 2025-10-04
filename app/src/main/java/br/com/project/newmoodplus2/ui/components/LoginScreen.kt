// File: IntroScreen.kt
package br.com.project.moodplus.components

import android.widget.Toast
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.project.newmoodplus.ui.viewmodel.LoginState
import br.com.project.newmoodplus.ui.viewmodel.LoginViewModel
import br.com.project.newmoodplus.ui.viewmodel.factory.LoginScreenViewModelFactory
import br.com.project.newmoodplus2.R

@Composable
fun LoginScreen(
    navController: NavController
) {
    // 1. Get the context
    val context = LocalContext.current

    // 2. Create the ViewModel using its Factory
    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginScreenViewModelFactory(context.applicationContext)
    )

    // 3. Observe the login state from the ViewModel
    val loginState by loginViewModel.loginState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // 4. Use LaunchedEffect to handle navigation and errors
    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is LoginState.Success -> {
                // Navigate to the next screen on successful login
                navController.navigate("HomeScreen") {
                    // Clear the back stack so the user can't go back to the login screen
                    popUpTo("IntroScreen") { inclusive = true }
                }
            }
            is LoginState.Error -> {
                // Show an error message
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            else -> {
                // Do nothing for Idle or Loading states here
            }
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

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                // ... other properties
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                // ... other properties
            )

            Button(
                // 5. Call the login function from the ViewModel
                onClick = { loginViewModel.login(email, password) },
                // 6. Disable the button while loading
                enabled = loginState !is LoginState.Loading,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(220.dp, 90.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Yellow)),
                shape = RoundedCornerShape(20.dp)
            ) {
                // 7. Show a progress indicator when loading
                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.Dark)
                    )
                } else {
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    // For previews, it's best to use a rememberNavController
    LoginScreen(navController = rememberNavController())
}