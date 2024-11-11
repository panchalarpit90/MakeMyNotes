# MakeMyNotes

MakeMyNotes is a simple yet powerful note-taking Android application built with Kotlin, providing users the ability to create, edit, and store notes securely. The app uses modern Android development principles and technologies such as Room for local database management, SharedPreferences for storing user preferences, and Firebase for Google authentication.

## Features

- **Kotlin**: Built entirely using Kotlin for better performance and modern language features.
- **Room Database**: Notes are stored locally using Room with SQLite, allowing offline access and fast data retrieval.
- **SharedPreferences & Firebase**: Stores user data securely and manages authentication using Firebase, enabling Google Sign-In.
- **Google Authentication**: Seamless sign-in with Google for personalized note-taking.
- **Material Design**: Implements Material UI components for a sleek, modern, and user-friendly interface.
- **RecyclerView**: Efficiently displays notes in a scrollable list for easy management.

## How to Use

1. **Login Screen**:  
   - Upon launching the app, you'll be prompted to log in using your Google account.
   - Google Sign-In is integrated with Firebase Authentication for easy login.

2. **Notes Fragment**:  
   - After logging in, you'll land on the Notes Fragment, which displays a list of all saved notes.
   - To add a new note, click on the **FAB (Floating Action Button)** at the bottom-right corner.
   - This will take you to the **EditNoteFragment** where you can add a new note.

3. **Editing a Note**:  
   - In the **EditNoteFragment**, you can enter the title and description of your note.
   - After editing, you can either **save** the note or **delete** it using the respective buttons.
   - Press the **Save** button to store the note in the local database (Room).
   - Press the **Delete** button to remove the note from the list.

4. **Storing in Room**:  
   - The app uses Room to store notes locally, making it available for offline use.
   - All notes are saved with a unique ID (UID) for easy access and management.

## Video Demo

[[Video Demo](https://drive.google.com/file/d/1XpEp1AHnYq0ggFT7hUuS4szZIqjhrNWv/view?usp=drivesdk)](#)

## Download APK

[[Download APK from Google Drive](https://drive.google.com/file/d/1qg4MxA-ZjpEOATCUcuE8rc4kN4GTPrwx/view?usp=sharing)](#)
