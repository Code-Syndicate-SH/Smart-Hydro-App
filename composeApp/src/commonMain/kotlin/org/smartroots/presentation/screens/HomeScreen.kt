package org.smartroots.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.smartroots.presentation.viewmodel.HomeViewModel
import smartroots.composeapp.generated.resources.Res
import smartroots.composeapp.generated.resources.ic_ec
import smartroots.composeapp.generated.resources.ic_humidity
import smartroots.composeapp.generated.resources.ic_lights
import smartroots.composeapp.generated.resources.ic_soil_ph
import smartroots.composeapp.generated.resources.ic_temperature
import smartroots.composeapp.generated.resources.ic_water

const val GET_SENSOR_DATA_DELAY_MS: Long = 5 * 1000

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel<HomeViewModel>()) {

    val uiState by viewModel.homeUIState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.fetchSensorPeriodically(GET_SENSOR_DATA_DELAY_MS)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF7EE)) // Green-tinted background
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sensor Dashboard",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        SensorCard(
            title = "Water Level",
            iconRes = Res.drawable.ic_water,
            value = "${uiState.sensorReadings["flowRate"] ?: 0.0} %"
        )
        SensorCard(
            title = "Temperature",
            iconRes = Res.drawable.ic_temperature,
            value = "${uiState.sensorReadings["temperature"] ?: 0.0}"
        )
        SensorCard(
            title = "Soil pH",
            iconRes = Res.drawable.ic_soil_ph,
            value = "${uiState.sensorReadings["pH"] ?: 0.0} pH"
        )

        SensorCard(
            title = "Electrical Conductivity",
            iconRes = Res.drawable.ic_ec,
            value = "${uiState.sensorReadings["eC"] ?: 0.0} µS/cm"
        )



        SensorCard(
            title = "Lights",
            iconRes = Res.drawable.ic_lights,
            value = "${uiState.sensorReadings["light"] ?: 0.0} %"
        )

        SensorCard(
            title = "Humidity",
            iconRes = Res.drawable.ic_humidity,
            value = "${uiState.sensorReadings["humidity"] ?: 0.0} %"
        )
    }
}

@Composable
fun SensorCard(title: String, iconRes: DrawableResource, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),

        ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = "$title Icon",
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = value, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Composable
fun Header(title: String, languages: List<String>) {
    Row() {
        Text(
            text = "Sensor Dashboard",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

}

@Preview()
@Composable
fun PreviewHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Sensor Dashboard",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        SensorCard("Soil pH", Res.drawable.ic_soil_ph, "6.5 pH")
        SensorCard("Electrical Conductivity", Res.drawable.ic_ec, "1400 µS/cm")
        SensorCard("Water Level", Res.drawable.ic_water, "75 %")
        SensorCard("Lights", Res.drawable.ic_lights, "50 %")
        SensorCard("Humidity", Res.drawable.ic_humidity, "25 %")
    }
}

