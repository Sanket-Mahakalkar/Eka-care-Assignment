# Eka-care-Assignment
# Steps to Set Up the Project
Clone the Repository
git clone https://github.com/Sanket-Mahakalkar/Eka-care-Assignment.git
cd project

Open in Android Studio

Open Android Studio (preferably Koala version or later).

Select Open an Existing Project and navigate to the cloned repository.

Build the Project
Ensure that Gradle syncs successfully.

If any dependencies fail to resolve, run:
./gradlew build

Run the App
Connect an emulator or a real device.
Click Run ▶️ in Android Studio or use the command:

# Libraries Used and Their Purpose
Hilt - Dependency Injection
ViewModel & LiveData - MVVM architecture components

Retrofit - API communication

Room - Local database management

Navigation Component - Fragment navigation


Material Components - UI styling and theming

# Architecture and Design Choices

MVVM Architecture

Model: Handles data management (local database using Room, API calls using Retrofit).

ViewModel: Stores and manages UI-related data and business logic.

View (Activity/Fragment): Observes LiveData from ViewModel and updates the UI accordingly.
