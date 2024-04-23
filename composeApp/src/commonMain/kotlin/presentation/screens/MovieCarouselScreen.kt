package presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import nichefilms.composeapp.generated.resources.Res
import nichefilms.composeapp.generated.resources.temp_placeholder
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.navigation.MovieCarouselComponenet

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MovieCarouselScreen(component: MovieCarouselComponenet) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            Image(painterResource(Res.drawable.temp_placeholder), "")
            Text("Name Of Movie")
            Text("Description of movie, that is longer than the title and provides more information")
        }
    }
}