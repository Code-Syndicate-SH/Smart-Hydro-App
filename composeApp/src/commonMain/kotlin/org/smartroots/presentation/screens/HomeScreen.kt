package org.smartroots.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.smartroots.presentation.viewmodel.HomeViewModel
import smartroots.composeapp.generated.resources.Res
import smartroots.composeapp.generated.resources.ic_ec
import smartroots.composeapp.generated.resources.ic_humidity
import smartroots.composeapp.generated.resources.ic_lights
import smartroots.composeapp.generated.resources.ic_soil_ph
import smartroots.composeapp.generated.resources.ic_water

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
               onComponentClick: (String, String, DrawableResource) -> Unit) {

    val uiState by viewModel.homeUIState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF7EE)) // Green-tinted background
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Sensor Dashboard",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SensorCard(
            title = "Soil pH",
            iconRes = Res.drawable.ic_soil_ph,
            value = "${uiState.sensorReadings["soilPH"] ?: 0.0} pH",
            onClick = { onComponentClick("Soil pH", "${uiState.sensorReadings["soilPH"] ?: 0.0} pH", Res.drawable.ic_soil_ph) }
        )

        SensorCard(
            title = "Electrical Conductivity",
            iconRes = Res.drawable.ic_ec,
            value = "${uiState.sensorReadings["electricalConductivity"] ?: 0.0} µS/cm",
            onClick = { onComponentClick("Electrical Conductivity", "${uiState.sensorReadings["electricalConductivity"] ?: 0.0} µS/cm", Res.drawable.ic_ec) }
        )

        SensorCard(
            title = "Water Level",
            iconRes = Res.drawable.ic_water,
            value = "${uiState.sensorReadings["waterLevel"] ?: 0.0} %",
            onClick = { onComponentClick("Water Level", "${uiState.sensorReadings["waterLevel"] ?: 0.0} %", Res.drawable.ic_water) }
        )

        SensorCard(
            title = "Lights",
            iconRes = Res.drawable.ic_lights,
            value = "${uiState.sensorReadings["lights"] ?: 0.0} %",
            onClick = { onComponentClick("Lights", "${uiState.sensorReadings["lights"] ?: 0.0} %", Res.drawable.ic_lights) }
        )

        SensorCard(
            title = "Humidity",
            iconRes = Res.drawable.ic_humidity,
            value = "${uiState.sensorReadings["humidity"] ?: 0.0} %",
            onClick = { onComponentClick("Humidity", "${uiState.sensorReadings["humidity"] ?: 0.0} %", Res.drawable.ic_humidity) }
        )
    }
}

@Composable
fun SensorCard(title: String, iconRes: DrawableResource, value: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
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


/*
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SensorCard("Soil pH", Res.drawable.ic_soil_ph, "6.5 pH")
        SensorCard("Electrical Conductivity", Res.drawable.ic_ec, "1400 µS/cm")
        SensorCard("Water Level", Res.drawable.ic_water, "75 %")
        SensorCard("Lights", Res.drawable.ic_lights, "50 %")
        SensorCard("Humidity", Res.drawable.ic_humidity, "25 %")
    }
}

*/