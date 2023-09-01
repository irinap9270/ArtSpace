package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                GalleryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryApp() {

    var currentStep by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "243 Gallery",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(174, 214, 241)
                )
            )
        }
    ) { innerPading ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPading)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (currentStep) {
                1-> {
                    GalleryUI(
                        stepNum = currentStep,
                        onPreviousClick = {},
                        onNextClick = {
                            currentStep = 2
                        }
                    )
                }
                2-> {
                    GalleryUI(
                        stepNum = currentStep,
                        onPreviousClick = {
                            currentStep = 1
                        },
                        onNextClick = {
                            currentStep = 3
                        }
                    )
                }
                3-> {
                    GalleryUI(
                        stepNum = currentStep,
                        onPreviousClick = {
                            currentStep = 2
                        },
                        onNextClick = {
                            currentStep = 4
                        }
                    )
                }
                else -> {
                    GalleryUI(
                        stepNum = currentStep,
                        onPreviousClick = {
                            currentStep = 3
                        },
                        onNextClick = {
                            currentStep = 4
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GalleryUI(
    stepNum: Int,
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val imageResouce = when (stepNum) {
        1-> R.drawable.cat1
        2-> R.drawable.cat2
        3-> R.drawable.cat3
        else-> R.drawable.cat4
    }
    val titleResource = when (stepNum) {
        1-> R.string.title1
        2-> R.string.title2
        3-> R.string.title3
        else-> R.string.title4
    }
    val artistResouce = when (stepNum) {
        1-> R.string.artist1
        2-> R.string.artist2
        3-> R.string.artist3
        else-> R.string.artist4
    }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(all = 50.dp)
                .border(BorderStroke(3.dp, Color.LightGray))
        ) {
            Image(
                painter = painterResource(imageResouce),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.padding(all = 20.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 30.dp)
                .background(Color(235, 237, 239)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(titleResource),
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = stringResource(artistResouce),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = onPreviousClick,
                modifier = Modifier
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(Color(174, 214, 241))
            ) {
                Text(text = "Previous", color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(Color(174, 214, 241))
            ) {
                Text(text = "Next", color = Color.DarkGray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        GalleryApp()
    }
}