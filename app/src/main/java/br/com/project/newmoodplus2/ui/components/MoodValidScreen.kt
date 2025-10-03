package br.com.project.newmoodplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.project.newmoodplus.data.remote.MoodAPI
import br.com.project.newmoodplus.data.repository.MoodRepository
import br.com.project.newmoodplus.ui.viewmodel.MoodValidScreenViewModel
import br.com.project.newmoodplus2.R
import br.com.project.newmoodplus2.ui.viewmodel.factory.MoodValidScreenViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun MoodValidScreen(
    navController: NavController
) {
    val context = LocalContext.current

    // Usa o RetrofitInstance para criar o API
    val moodRepository = MoodRepository(context)

    val moodValidViewModel: MoodValidScreenViewModel = viewModel(
        factory = MoodValidScreenViewModelFactory(moodRepository)
    )

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

            Text(
                modifier = Modifier.size(327.dp, 78.dp),
                text = "Nos conte como você está hoje",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.Dark),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier.size(80.dp, 80.dp)
                        .clickable {
                            // Chamar função do ViewModel para registrar humor
                            moodValidViewModel.registerMood("happy")
                        },
                    painter = painterResource(id = R.drawable.happy),
                    contentDescription = "Happy Face"
                )

                Image(
                    modifier = Modifier.size(75.dp, 75.dp)
                        .clickable {
                            moodValidViewModel.registerMood("neutral")
                        },
                    painter = painterResource(id = R.drawable.neutral),
                    contentDescription = "Neutral Face"
                )

                Image(
                    modifier = Modifier.size(80.dp, 80.dp)
                        .clickable {
                            moodValidViewModel.registerMood("sad")
                        },
                    painter = painterResource(id = R.drawable.sad),
                    contentDescription = "Sad Face"
                )
            }
        }
    }
}