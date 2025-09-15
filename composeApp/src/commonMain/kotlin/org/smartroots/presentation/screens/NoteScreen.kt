package org.smartroots.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import smartroots.composeapp.generated.resources.Res
import smartroots.composeapp.generated.resources.ic_heading

@Composable
fun NoteScreen(onViewNotes: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp)) // Increase top padding

        Image(
            painter = painterResource(resource = Res.drawable.ic_heading),
            contentDescription = "Sawubona umlimi 👩‍🌾",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp)) // Increase space between image and buttons

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF121212)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = {
                    //  button click action


                },
                modifier = Modifier
                    .padding(10.dp)
                    .size(width = 330.dp, height = 240.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff00AFEF))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    /*   val painter = rememberAsyncImagePainter(
                           model = ImageRequest.Builder(LocalPlatformContext.current)
                               .data(Res.drawable.buttonation)
                               .decoderFactory(GifDecoder.Factory())
                               .size(Size.ORIGINAL)
                               .build()
                       )
                       Image(
                           painter = painter,
                           contentDescription = null,
                           modifier = Modifier.fillMaxSize()
                       )*/
                    Text(
                        text = "New Entry",
                        fontSize = 40.sp,

                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Increase space between buttons

            Button(
                onClick = {
                    onViewNotes()
                },
                modifier = Modifier
                    .padding(10.dp)
                    .size(width = 330.dp, height = 240.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA8CF45))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    /* val painter = rememberAsyncImagePainter(
                         model = ImageRequest.Builder(LocalPlatformContext.current)
                             .data(Res.drawable.buttonview)
                             .decoderFactory(GifDecoder.Factory())
                             .size(Size.ORIGINAL)
                             .build()
                     )*/
                    /*  Image(
                          painter = painter,
                          contentDescription = null,
                          modifier = Modifier.fillMaxSize()
                      )*/
                    Text(
                        text = "My Notes",
                        fontSize = 40.sp,

                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}