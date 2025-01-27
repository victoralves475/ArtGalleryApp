package br.edu.ifpb.artgalleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryApp()
        }
    }
}

@Composable
fun ArtGalleryApp() {
    val artworks = listOf(
        Artwork(
            imageRes = R.drawable.pikachu,
            title = "Pikachu",
            author = "Pokemon",
            year = "1994"
        ),
        Artwork(
            imageRes = R.drawable.hyoga,
            title = "Hyoga de cisne",
            author = "Cavaleiro dos Zodíaco",
            year = "1994"
            asdff
        )
    )

    var currentIndex by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(16.dp)
        ) {
            ArtworkDisplay(artwork = artworks[currentIndex])

            NavigationButtons(
                onPrevious = {
                    if (currentIndex > 0) currentIndex--
                },
                onNext = {
                    if (currentIndex < artworks.size - 1) currentIndex++
                }
            )
        }
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = artwork.title,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Informações
        Text(
            text = artwork.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "${artwork.author} (${artwork.year})",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun NavigationButtons(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onPrevious) {
            Text("Previous")
        }
        Button(onClick = onNext) {
            Text("Next")
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val author: String,
    val year: String
)

@Preview(showBackground = true)
@Composable
fun PreviewArtGalleryApp() {
    ArtGalleryApp()
}
