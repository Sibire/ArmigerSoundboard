package com.example.armigerssoundboard

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.armigerssoundboard.ui.theme.ArmigersSoundboardTheme
import androidx.core.graphics.toColorInt
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArmigersSoundboardTheme {
                Soundboard(
                    onPlay = {
                        mediaPlayer?.release()
                        mediaPlayer = MediaPlayer.create(this, R.raw.fox_nfl_theme)
                        mediaPlayer?.start()
                    },
                    onStop = {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

@Composable
fun Soundboard(onPlay: () -> Unit, onStop: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onPlay) {
                Text("Play NFL Theme")
            }
            Button(onClick = onStop) {
                Text("Stop All Sounds")
            }
        }
        Text(
            text = "I know my girlfriend is a sociopath because she asked me to do Android development.",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = Color("#a600ff".toColorInt())
        )
    }
}

@Preview(
    name = "Soundboard Preview",
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun SoundboardPreview() {
    ArmigersSoundboardTheme {
        Soundboard(onPlay = {}, onStop = {})
    }
}