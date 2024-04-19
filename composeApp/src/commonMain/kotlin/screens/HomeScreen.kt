package screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cilo.app.presentation.components.theme.Grey20
import com.cilo.app.presentation.components.theme.Orange80
import com.cilo.app.presentation.components.theme.fontDarkGray
import com.cilo.app.presentation.components.theme.lightGreyReferralCodeBorder
import navigation.BuildingCalculationComponent
import navigation.BuildingCalculationEvent

@Composable
fun HomeScreen(component: BuildingCalculationComponent) {
    val area = remember { mutableStateOf(TextFieldValue("")) }
    val height = remember { mutableStateOf(TextFieldValue("")) }
    val occupants = remember { mutableStateOf(TextFieldValue("")) }
    val doors = remember { mutableStateOf(TextFieldValue("")) }
    val windows = remember { mutableStateOf(TextFieldValue("")) }
    val BTUs = remember { mutableStateOf(0) }
    val showTotal = remember { mutableStateOf(false) }
    val genreList = listOf("Horror", "Action", "Comedy")
    val selectedGenre = remember { mutableStateOf(TextFieldValue("")) }
    val genreExpanded = remember { mutableStateOf(false) }
    val selectedGenreIndex = remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(Modifier.padding(vertical = 16.dp, horizontal = 32.dp)) {
            Box(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                TextStartAlignedDropDownBox(text = selectedGenre.value.text, label = "Select genre") {
                    genreExpanded.value = !genreExpanded.value
                }
                DropdownMenu(
                    expanded = genreExpanded.value,
                    onDismissRequest = { genreExpanded.value = false }
                ) {
                    genreList.forEachIndexed { index, duration ->
                        DropdownMenuItem(
                            text = { Text(text = duration) },
                            onClick = {
                                selectedGenreIndex.intValue = index
                                genreExpanded.value = false
                                selectedGenre.value = TextFieldValue(duration)
                            }
                        )
                    }
                }
            }
        }
        Button(
            onClick = {
                showTotal.value = true
                BTUs.value =
                    (area.value.text.toInt() * height.value.text.toInt()) + (occupants.value.text.toInt() * 100) + (doors.value.text.toInt() * 1000) + (windows.value.text.toInt() * 1000)
            },
            Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Calculate")
        }
        AnimatedVisibility(showTotal.value) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${BTUs.value} BTUs")
                val tons = (BTUs.value / 12000F)
                Text(text = "$tons tons")
            }
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Button(
                    onClick = { component.onEvent(BuildingCalculationEvent.ClickNext) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun TextInputField(
    text: MutableState<TextFieldValue>,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { value -> text.value = value },
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        label = { Text(label, fontSize = 13.sp) },
        textStyle = TextStyle(fontSize = 13.sp),
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = fontDarkGray,
            cursorColor = Orange80,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            disabledContainerColor = lightGreyReferralCodeBorder,
            focusedIndicatorColor = fontDarkGray,
            unfocusedIndicatorColor = lightGreyReferralCodeBorder,
            disabledIndicatorColor = lightGreyReferralCodeBorder
        )
    )
}

@Composable
fun TextStartAlignedDropDownBox(
    text: String,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typeText = text.ifEmpty { label }
        Text(
            text = typeText,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp, vertical = 16.dp),
            color = Grey20,
            fontSize = 13.sp
        )
        Icon(
            Icons.Default.KeyboardArrowDown,
            contentDescription = "",
            tint = Grey20
        )
    }
}