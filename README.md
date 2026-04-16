# Weather App

A simple Android Weather Application that provides weather forecasts for cities worldwide. Built with modern Android development tools including Jetpack Compose, Clean Architecture, and Hilt.

## Setup Instructions

### Prerequisites
- Android Studio Ladybug or later
- JDK 11 or higher

### API Configuration
The app uses [OpenWeatherMap API](https://openweathermap.org/api) to fetch weather data.

1.  Obtain an API key from OpenWeatherMap.
2.  In the project root, locate or create a `local.properties` file.
3.  Add your API key to the `local.properties` file:
    ```properties
    WEATHER_API_KEY=your_api_key_here
    ```

> **Note:** If `WEATHER_API_KEY` is not provided in `local.properties`, the app will automatically use a **free fallback API key** defined in `Constants.kt`.

## Dependencies & Implementation

The project utilizes several key libraries for its functionality:

### UI & Presentation
- **Jetpack Compose**: Used for building the entire declarative UI.
- **Material3**: Implements Material Design 3 components for a modern look and feel.
- **Compose Foundation**: Provides fundamental UI building blocks.
- **Lifecycle Runtime & ViewModel**: Manages UI state and follows the Android Lifecycle.
- **Coil (Compose & OkHttp)**: Used for asynchronous image loading and caching of weather icons.

### Dependency Injection
- **Hilt (Dagger)**: Handles dependency injection throughout the app.
- **Hilt Navigation Compose**: Integrates Hilt with Jetpack Compose navigation.

### Networking
- **Retrofit**: A type-safe HTTP client for Android, used to interface with the OpenWeatherMap API.
- **Gson & Converter-Gson**: Handles JSON serialization and deserialization of API responses.
- **Logging Interceptor**: Intercepts and logs HTTP requests and responses for debugging purposes.

### Local Database
- **Room**: An abstraction layer over SQLite used for local data persistence and offline support.
- **KSP (Kotlin Symbol Processing)**: Used for Room and Hilt annotation processing.

### Utilities & Other
- **Kotlin Coroutines**: Manages asynchronous operations and prevents UI blocking.
- **Kotlin Serialization**: Used for data serialization needs.
- **Core KTX**: Provides Kotlin extensions for Android framework APIs.

## Architecture
The app follows Clean Architecture principles with a clear separation of concerns:
- **Presentation**: UI components (Compose) and ViewModels.
- **Domain**: Repository interfaces and business logic.
- **Data**: Remote API (Retrofit) and Local Database (Room) implementations.

**License**
---
The code in this project is licensed under the Apache License 2.0.

    Copyright 2018 Google LLC

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

**Disclaimer**
---
* This is not an officially supported Google product.

[1]: https://kotlinlang.org/
[3]: https://developer.android.com/topic/libraries/architecture
[5]: https://developer.android.com/topic/libraries/architecture/viewmodel
[6]: https://developer.android.com/training/dependency-injection/hilt-android
[7]: https://kotlinlang.org/docs/coroutines-overview.html
[8]: https://kotlinlang.org/docs/flow.html
[9]: https://developer.android.com/jetpack/compose
[10]: https://medium.com/firebase-tips-tricks/how-to-read-data-from-room-using-kotlin-flow-in-jetpack-compose-7a720dec35f5
[12]: https://developer.android.com/guide/navigation
[13]: https://developer.android.com/training/data-storage/room
