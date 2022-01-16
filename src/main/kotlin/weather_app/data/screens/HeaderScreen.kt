package weather_app.data.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.backgroundColor
import weather_app.common.CommonCard

@Preview
@Composable
fun Header() {
    val search = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor),
    ) {


        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(

            ) {
                Image(painterResource("person.png"), contentDescription = "person")
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 10.dp)
                ) {
                    Text("Hello,")
                    Text("What's the Weather", fontSize = 20.sp)
                }
            }
            Row(
            ) {
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                    },
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)).width(250.dp).height(50.dp),
                    placeholder = { Text("search city") },
                    trailingIcon = { Icon(Icons.Default.Search, "", tint = Color.Blue) },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    maxLines = 1,
                    singleLine = true
                )
            }

        }

        WeatherScreen()
    }
}

@Composable
@Preview
fun WeatherScreen() {
    val maxTemp = remember { mutableStateOf("12°C") }
    val minTemp = remember { mutableStateOf("7°C") }
    val weather = remember { mutableStateOf("partial cloud") }
    val pressure = remember { mutableStateOf("800mb") }
    val humidity = remember { mutableStateOf("87%") }
    val visibility = remember { mutableStateOf("4.3km") }
    val city = remember { mutableStateOf("New Delhi") }
    Column(
        modifier = Modifier.padding(50.dp)
    ) {
        Row {
            Text(city.value, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(maxTemp.value, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 5.dp,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    minTemp.value,
                    modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row {
            Text(weather.value)
        }
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            CommonCard(
                color = Color.Black,
                title = "Pressure",
                des = pressure.value,
                textColor = Color.White
            )

            CommonCard(
                color = Color.Yellow,
                title = "Visibility",
                des = visibility.value,
                textColor = Color.Black
            )

            CommonCard(
                color = Color.Green,
                title = "Humidity",
                des = humidity.value,
                textColor = Color.Black
            )
        }
    }


}
