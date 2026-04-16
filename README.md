# WhatNowApp
Simple News App 

WhatNow App

WhatNow is a modern Android news application that delivers the latest headlines using a clean UI and efficient architecture. The app is built with best practices in Android development, including MVVM, Retrofit, and Coroutines.

🚀 Features
📰 Fetch latest news from API
⚡ Fast and responsive UI using RecyclerView
🧠 MVVM Architecture for clean code separation
🔄 Asynchronous API calls using Kotlin Coroutines
🌐 Retrofit for network requests
🎬 Splash Screen using Android 12 SplashScreen API
🏗️ Tech Stack
Language: Kotlin
Architecture: MVVM (Model - View - ViewModel)
Networking: Retrofit
Async Handling: Coroutines
UI Components: RecyclerView, XML
Lifecycle Awareness: ViewModel, LiveData

📂 Project Structure
com.example.whatnowapp
│
├── data
│   ├── api          # Retrofit API Service
│   ├── repository   # Data handling layer
│
├── ui
│   ├── view         # Activities / Fragments
│   ├── adapter      # RecyclerView Adapter
│
├── viewmodel        # ViewModels
🔧 Setup & Installation

Clone the repository:

git clone https://github.com/your-username/WhatNowApp.git
Open in Android Studio

Add your API key in:

local.properties

or directly in your API service (not recommended for production)

Run the app 🚀
🌍 API Used
News API (or your chosen API)
