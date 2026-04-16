package com.sohailabbas.weather_app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.sohailabbas.weather_app.data.remote.dto.DailyWeatherUiModel
import com.sohailabbas.weather_app.presentation.WeatherViewModel
import com.sohailabbas.weather_app.presentation.screens.WeatherScreen
import com.sohailabbas.weather_app.util.Response
import com.sohailabbas.weather_app.util.TemperatureUnit
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherTemperatureToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel = mockk<WeatherViewModel>(relaxed = true)
    
    // Use mutableStateOf so Compose can observe changes in the mock
    private val unitState = mutableStateOf(TemperatureUnit.CELSIUS)

    @Before
    fun setup() {
        val mockData = listOf(
            DailyWeatherUiModel(
                date = "2023-10-27",
                description = "Cloudy",
                maxTemp = 20.0,
                minTemp = 10.0,
                icon = "01d",
                hourly = listOf(
                    DailyWeatherUiModel.HourlyUiModel(
                        time = "12:00 PM",
                        temp = 15.0,
                        icon = "01d"
                    )
                )
            )
        )
        
        val weatherState = MutableStateFlow<Response<List<DailyWeatherUiModel>>>(Response.Success(mockData))
        
        every { mockViewModel.weatherForecastState } returns weatherState
        every { mockViewModel.city } returns "London"
        
        // Reset state for every test
        unitState.value = TemperatureUnit.CELSIUS
        
        // Mock the property and the toggle function
        every { mockViewModel.temperatureUnit } answers { unitState.value }
        every { mockViewModel.toggleTemperatureUnit() } answers {
            unitState.value = if (unitState.value == TemperatureUnit.CELSIUS) {
                TemperatureUnit.FAHRENHEIT
            } else {
                TemperatureUnit.CELSIUS
            }
        }
    }

    @Test
    fun initialState_DisplaysCelsius() {
        startScreen()

        // Daily Temps
        composeTestRule.onNodeWithText("20°C").assertIsDisplayed()
        composeTestRule.onNodeWithText("10°C").assertIsDisplayed()
        // Hourly Temp
        composeTestRule.onNodeWithText("15°C").assertIsDisplayed()
        // Label
        composeTestRule.onNodeWithText("Celsius (°C)").assertIsDisplayed()
    }

    @Test
    fun toggleToFahrenheit_UpdatesDailyTemperatures() {
        startScreen()

        // Toggle to Fahrenheit
        composeTestRule.onNodeWithTag("unit_toggle_row").performClick()

        // 20°C -> 68°F
        // 10°C -> 50°F
        composeTestRule.onNodeWithText("68°F").assertIsDisplayed()
        composeTestRule.onNodeWithText("50°F").assertIsDisplayed()
        composeTestRule.onNodeWithText("Fahrenheit (°F)").assertIsDisplayed()
    }

    @Test
    fun toggleToFahrenheit_UpdatesHourlyTemperatures() {
        startScreen()

        // Toggle to Fahrenheit
        composeTestRule.onNodeWithTag("unit_toggle_row").performClick()

        // 15°C -> 59°F
        composeTestRule.onNodeWithText("59°F").assertIsDisplayed()
    }

    @Test
    fun toggleBackToCelsius_RestoresOriginalValues() {
        startScreen()

        // Toggle to Fahrenheit then back to Celsius
        composeTestRule.onNodeWithTag("unit_toggle_row").performClick()
        composeTestRule.onNodeWithTag("unit_toggle_row").performClick()

        composeTestRule.onNodeWithText("20°C").assertIsDisplayed()
        composeTestRule.onNodeWithText("10°C").assertIsDisplayed()
        composeTestRule.onNodeWithText("15°C").assertIsDisplayed()
        composeTestRule.onNodeWithText("Celsius (°C)").assertIsDisplayed()
    }

    private fun startScreen() {
        composeTestRule.setContent {
            WeatherScreen(
                paddingValues = PaddingValues(0.dp),
                weatherViewModel = mockViewModel
            )
        }
    }
}
