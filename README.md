# Country List Viewer
This project demonstrates a simple Android application that fetches and displays a list of countries. It's a showcase of using modern Android development tools and practices without relying on Jetpack Compose or Dagger for dependency injection.
## Features
Fetches a list of countries in JSON format from a provided URL.
Displays the countries in a RecyclerView, ordered as they appear in the JSON data.
Each item in the list shows the country's name, region, code, and capital.
Robust implementation, with error handling and support for device rotation.
## Technical Details
Networking: Implemented using Retrofit and OkHttp.
JSON Parsing: Utilizing Gson for handling JSON data.
Concurrency: Leveraging Kotlin Coroutines for asynchronous tasks.
Architecture: Following MVVM (Model-View-ViewModel) pattern for clean separation of concerns.
