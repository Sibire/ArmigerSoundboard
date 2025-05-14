package com.example.armigerssoundboard
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Alignment
import com.example.armigerssoundboard.ui.theme.ArmigersSoundboardTheme

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
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}