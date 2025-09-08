package org.smartroots.presentation.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import smartroots.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ComponentScreen(
    title: String,
    value: String,
    iconRes: DrawableResource
) {
    var isOn by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Choose animation based on component title
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF7EE))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Image(
            painter = painterResource(iconRes),
            contentDescription = "$title Icon",
            modifier = Modifier.size(120.dp).then(animatedModifier)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Current Value: $value",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { isOn = !isOn },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(if (isOn) "On" else "Off")
        }
    }
}


@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun PreviewComponentScreen() {
    Column {
        ComponentScreen("Soil pH", "6.5 pH", Res.drawable.ic_soil_ph)
        ComponentScreen("Electrical Conductivity", "1400 µS/cm", Res.drawable.ic_ec)
        ComponentScreen("Water Level", "75%", Res.drawable.ic_water)
        ComponentScreen("Lights", "60%", Res.drawable.ic_lights)
        ComponentScreen("Humidity", "45%", Res.drawable.ic_humidity)
        ComponentScreen("Temperature", "22°C", Res.drawable.ic_temperature)
    }
}
