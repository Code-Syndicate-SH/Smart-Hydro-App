package org.smartroots.presentation.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.smartroots.presentation.viewmodel.HomeViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import smartroots.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreenComponents(
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.homeUIState.collectAsState()

    val sensorItems: List<Triple<String, String, DrawableResource>> = listOf(
        Triple("Soil pH", uiState.sensorReadings["soilPH"]?.toString() ?: "--", Res.drawable.ic_soil_ph),
        Triple("Electrical Conductivity", uiState.sensorReadings["electricalConductivity"]?.toString() ?: "--", Res.drawable.ic_ec),
        Triple("Water Level", uiState.sensorReadings["waterLevel"]?.toString() ?: "--", Res.drawable.ic_water),
        Triple("Lights", uiState.sensorReadings["lights"]?.toString() ?: "--", Res.drawable.ic_lights),
        Triple("Humidity", uiState.sensorReadings["humidity"]?.toString() ?: "--", Res.drawable.ic_humidity),
        Triple("Temperature", uiState.sensorReadings["temperature"]?.toString() ?: "--", Res.drawable.ic_temperature),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF7EE))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Sensor Dashboard",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        sensorItems.forEach { (title, value, icon) ->
            SensorCard(title = title, value = value, iconRes = icon)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AnimatedSensorCard(title: String, value: String, iconRes: DrawableResource) {
    var isOn by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val animatedModifier = when (title.lowercase()) {
        "water level" -> {
            val scale by infiniteTransition.animateFloat(
                1f, 1.2f,
                animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
                label = "waterScale"
            )
            Modifier.graphicsLayer(scaleX = if (isOn) scale else 1f, scaleY = if (isOn) scale else 1f)
        }
        "lights" -> {
            val alpha by infiniteTransition.animateFloat(
                0.5f, 1f,
                animationSpec = infiniteRepeatable(tween(600), RepeatMode.Reverse),
                label = "lightsGlow"
            )
            Modifier.graphicsLayer(alpha = if (isOn) alpha else 1f)
        }
        "humidity" -> {
            val alpha by infiniteTransition.animateFloat(
                0.3f, 1f,
                animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
                label = "humidityPulse"
            )
            Modifier.graphicsLayer(alpha = if (isOn) alpha else 1f)
        }
        "soil ph" -> {
            val offsetY by infiniteTransition.animateFloat(
                0f, -10f,
                animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
                label = "soilBounce"
            )
            Modifier.graphicsLayer(translationY = if (isOn) offsetY else 0f)
        }
        "electrical conductivity" -> {
            val offsetX by infiniteTransition.animateFloat(
                -5f, 5f,
                animationSpec = infiniteRepeatable(tween(200), RepeatMode.Reverse),
                label = "ecShake"
            )
            Modifier.graphicsLayer(translationX = if (isOn) offsetX else 0f)
        }
        "temperature" -> {
            val rotation by infiniteTransition.animateFloat(
                0f, 360f,
                animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Restart),
                label = "tempRotate"
            )
            Modifier.graphicsLayer(rotationZ = if (isOn) rotation else 0f)
        }
        else -> Modifier
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(48.dp)
                    .then(animatedModifier)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = value, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { isOn = !isOn },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(if (isOn) "On" else "Off")
            }
        }
    }
}

@OptIn(org.jetbrains.compose.resources.ExperimentalResourceApi::class)
@Preview
@Composable
fun PreviewHomeScreenComponents() {
    val dummySensorItems = listOf(
        Triple("Soil pH", "6.5", Res.drawable.ic_soil_ph),
        Triple("Electrical Conductivity", "1400 µS/cm", Res.drawable.ic_ec),
        Triple("Water Level", "75%", Res.drawable.ic_water),
        Triple("Lights", "60%", Res.drawable.ic_lights),
        Triple("Humidity", "45%", Res.drawable.ic_humidity),
        Triple("Temperature", "22°C", Res.drawable.ic_temperature),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF7EE))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Sensor Dashboard (Preview)",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        dummySensorItems.forEach { (title, value, icon) ->
            SensorCard(title = title, value = value, iconRes = icon)
        }
    }
}



