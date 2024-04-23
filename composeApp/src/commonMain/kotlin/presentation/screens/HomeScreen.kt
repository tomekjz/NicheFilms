package presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
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
import presentation.navigation.HomeScreenComponent
import presentation.navigation.HomeScreenEvent

@Composable
fun HomeScreen(component: HomeScreenComponent) {
    val genreList = listOf("Horror", "Action", "Comedy")
    val selectedGenre = remember { mutableStateOf(TextFieldValue("")) }
    val genreExpanded = remember { mutableStateOf(false) }

    val budgetList = listOf("Under $5 million (Indie)", "$5-50 million (Middle-ground)", "$50+ million (Blockbuster)")
    val selectedBudget = remember { mutableStateOf(TextFieldValue("")) }
    val budgetExpanded = remember { mutableStateOf(false) }

    val countryList = listOf("UK", "US", "Japan")
    val selectedCountry = remember { mutableStateOf(TextFieldValue("")) }
    val countryExpanded = remember { mutableStateOf(false) }

    val decadeList = listOf("2020s", "2010s", "2000s")
    val selectedDecade = remember { mutableStateOf(TextFieldValue("")) }
    val decadeExpanded = remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        val scrollState = rememberScrollState()
        Spacer(Modifier.height(16.dp))
        Text("Let's find you a movie to watch")
        Column(Modifier.padding(horizontal = 48.dp, vertical = 16.dp).verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
            //Genres
            Box(Modifier.padding(vertical = 16.dp)) {
                TextStartAlignedDropDownBox(text = selectedGenre.value.text, label = "Select a genre") {
                    genreExpanded.value = !genreExpanded.value
                }
                DropdownMenu(
                    expanded = genreExpanded.value,
                    onDismissRequest = { genreExpanded.value = false }
                ) {
                    genreList.forEach { genre ->
                        DropdownMenuItem(
                            text = { Text(text = genre) },
                            onClick = {
                                genreExpanded.value = false
                                selectedGenre.value = TextFieldValue(genre)
                            }
                        )
                    }
                }
            }

            //Budgets
            AnimatedVisibility(selectedGenre.value.text.isNotEmpty()) {
                Box(Modifier.padding(vertical = 16.dp)) {
                    TextStartAlignedDropDownBox(
                        text = selectedBudget.value.text,
                        label = "Select a budget"
                    ) {
                        budgetExpanded.value = !budgetExpanded.value
                    }
                    DropdownMenu(
                        expanded = budgetExpanded.value,
                        onDismissRequest = { budgetExpanded.value = false }
                    ) {
                        budgetList.forEach { budget ->
                            DropdownMenuItem(
                                text = { Text(text = budget) },
                                onClick = {
                                    budgetExpanded.value = false
                                    selectedBudget.value = TextFieldValue(budget)
                                }
                            )
                        }
                    }
                }
            }

            //Countries
            AnimatedVisibility(selectedBudget.value.text.isNotEmpty()) {
                Box(Modifier.padding(vertical = 16.dp)) {
                    TextStartAlignedDropDownBox(
                        text = selectedCountry.value.text,
                        label = "Select a country"
                    ) {
                        countryExpanded.value = !countryExpanded.value
                    }
                    DropdownMenu(
                        expanded = countryExpanded.value,
                        onDismissRequest = { countryExpanded.value = false }
                    ) {
                        countryList.forEach { country ->
                            DropdownMenuItem(
                                text = { Text(text = country) },
                                onClick = {
                                    countryExpanded.value = false
                                    selectedCountry.value = TextFieldValue(country)
                                }
                            )
                        }
                    }
                }
            }

            //Decades
            AnimatedVisibility(selectedCountry.value.text.isNotEmpty()) {
                Box(Modifier.padding(vertical = 16.dp)) {
                    TextStartAlignedDropDownBox(
                        text = selectedDecade.value.text,
                        label = "Select a decade"
                    ) {
                        decadeExpanded.value = !decadeExpanded.value
                    }
                    DropdownMenu(
                        expanded = decadeExpanded.value,
                        onDismissRequest = { decadeExpanded.value = false }
                    ) {
                        decadeList.forEach { decade ->
                            DropdownMenuItem(
                                text = { Text(text = decade) },
                                onClick = {
                                    decadeExpanded.value = false
                                    selectedDecade.value = TextFieldValue(decade)
                                }
                            )
                        }
                    }
                }
            }
            if (selectedCountry.value.text.isNotEmpty()) {
                LaunchedEffect(Unit) {
                    scrollState.scrollTo(scrollState.maxValue)
                }
            }
        }
        AnimatedVisibility(selectedGenre.value.text.isNotEmpty()
                && selectedBudget.value.text.isNotEmpty()
                && selectedCountry.value.text.isNotEmpty()
                && selectedDecade.value.text.isNotEmpty()
        ) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Button(
                    onClick = { component.onEvent(HomeScreenEvent.ClickNext) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text("Spin the wheel")
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