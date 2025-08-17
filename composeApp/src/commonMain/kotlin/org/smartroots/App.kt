package org.smartroots

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import smartroots.composeapp.generated.resources.Res
import smartroots.composeapp.generated.resources.ic_soil_ph
import smartroots.composeapp.generated.resources.ic_temperature
import smartroots.composeapp.generated.resources.ic_water
import smartroots.composeapp.generated.resources.ic_lights
import smartroots.composeapp.generated.resources.ic_humidity
import smartroots.composeapp.generated.resources.ic_ec
import smartroots.composeapp.generated.resources.ic_notes
import smartroots.composeapp.generated.resources.ic_camera



data class MonitoringOption(
    val title: String,
    val iconRes: DrawableResource,
    val backgroundColor: Color
)



@Composable
fun MonitoringCard(option: MonitoringOption) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = option.backgroundColor
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = option.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(option.iconRes),
                    contentDescription = option.title,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


