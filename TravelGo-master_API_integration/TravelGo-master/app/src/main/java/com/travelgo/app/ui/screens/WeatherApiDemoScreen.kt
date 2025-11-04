
package com.travelgo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.travelgo.app.viewmodel.WeatherViewModel
import com.travelgo.app.viewmodel.WeatherUiState
import com.travelgo.app.BuildConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApiDemoScreen(vm: WeatherViewModel = viewModel()) {
    var city by remember { mutableStateOf(TextFieldValue("Santiago")) }

    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        if (state.data == null && !state.isLoading) {
            vm.load(city.text, BuildConfig.OPEN_WEATHER_API_KEY)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Demo API Clima", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Ciudad") }
        )
        Button(
            onClick = { vm.load(city.text, BuildConfig.OPEN_WEATHER_API_KEY) },
            enabled = !state.isLoading
        ) { Text("Consultar") }

        when {
            state.isLoading -> {
                CircularProgressIndicator()
                Text("Cargando...")
            }
            state.error != null -> {
                Text("❌ ${state.error}", color = MaterialTheme.colorScheme.error)
            }
            state.data != null -> {
                Card {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Ciudad: ${state.data!!.city}", style = MaterialTheme.typography.titleMedium)
                        Text("Temperatura: ${state.data!!.tempC}")
                        Text("Condiciones: ${state.data!!.description}")
                    }
                }
            }
        }
        Divider(Modifier.padding(top = 8.dp))
        Text("Clave API usada: ${if (BuildConfig.OPEN_WEATHER_API_KEY == "REPLACE_ME") "FALTA CONFIGURAR" else "OK"}",
            style = MaterialTheme.typography.labelMedium)
    }
}
