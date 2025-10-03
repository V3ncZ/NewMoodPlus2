package br.com.project.newmoodplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.project.newmoodplus2.R

@Composable
fun HomeScreen(
    navController: NavController
){
    Box(modifier= Modifier.background(colorResource(id = R.color.lightBlue)).fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop
                )
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 22.dp)) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 16.sp,
                    text = "Seu mood de hoje",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(5.dp))

                //Adicionar os moods //
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp) // alinhado com os botões
                    .background(
                        color = colorResource(id = R.color.White),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {

                // Cabeçalho estilizado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.Yellow), shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Descrição",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.Dark)
                    )
                    Text(
                        "Resultado",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.Dark)
                    )
                    Text(
                        "Nível",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.Dark)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn {
                    // Adicionar a lazyColumn
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, top = 40.dp, end = 22.dp),
                Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { navController.navigate("MoodValidScreen") },
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(height = 45.dp, width = 170.dp)
                        .shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Yellow)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Realizar",
                        color = colorResource(id = R.color.Dark),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Button(
                    onClick = { navController.navigate("CalendarScreen") },
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(height = 45.dp, width = 170.dp)
                        .shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Yellow)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Calendário",
                        color = colorResource(id = R.color.Dark),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, top = 40.dp, end = 22.dp),
                Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate("GuidenceScreen") },
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(height = 45.dp, width = 170.dp)
                        .shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Yellow)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Eventos",
                        color = colorResource(id = R.color.Dark),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}