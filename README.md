# Quicktimer - Timer Application

## Overview

Quicktimer is an Android application that allows users to set and manage timers. It includes a splash screen, timer screen with control buttons, sound settings, and a timer history feature. It also stores user preferences like sound selection in `SharedPreferences` and timer history in `SQLite`.

### Features

1. **Splash Screen**: Displays the Amrita logo for 2 seconds before navigating to the home screen.
2. **Timer Screen**: 
   - Allows users to set a timer with hours, minutes, and seconds input.
   - Provides start, pause, and reset functionalities.
   - Plays a notification sound when the timer finishes.
3. **Sound Settings Screen**: 
   - Allows users to choose a sound for the timer's notification.
   - Previews sounds before selection.
4. **Timer History Screen**: 
   - Displays a list of previously set timers with duration and end time.
5. **SQLite**: Stores the timer history.
6. **Shared Preferences**: Saves the user's selected sound.

### Prerequisites

- Android Studio
- Android SDK
- An Android device or emulator for testing

### Instructions

1. Clone this repository or create a new Android project in Android Studio.
2. Place `amrita_logo.png` in the `res/drawable` folder.
3. Place the audio files (`sound1.mp3`, `sound2.mp3`, `sound3.mp3`) in the `res/raw` folder.
4. Copy the XML and Java files into their respective folders in Android Studio.
5. Build and run the app.

---
