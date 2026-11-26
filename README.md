# StudentDatabase Android Application

A lightweight, native Android application built in **Java** that provides a complete local CRUD (Create, Read, Update, Delete) interface for managing student records using **SQLite**.

---

## 🚀 Features

- **Add Students:** Register new students with their Name, Course, and Year of Study.
- **View Records:** Display all stored student records in real-time using a dynamic `ListView`.
- **Select & Edit:** Tap any student record from the list to populate the form fields instantly for updating or deleting.
- **Update Records:** Modify existing student information seamlessly.
- **Delete Records:** Remove student entries from the database with confirmation feedback.
- **Form Validation:** Input checks with instant feedback via Android `Toast` messages.
- **Local Persistence:** Powered by Android's native `SQLiteOpenHelper`.

---

## 📱 Tech Stack

- **Language:** Java
- **UI Toolkit:** XML Layouts (LinearLayout / ConstraintLayout)
- **Database:** SQLite (`SQLiteOpenHelper`, `ContentValues`, `Cursor`)
- **Min SDK:** API 26 (Android 8.0 Oreo)
- **Target SDK:** API 37
- **Build System:** Gradle (Kotlin DSL)

---

## 🛠️ Setup & Installation Instructions

Follow the steps below to clone and run this project in Android Studio:

### 1. Prerequisites
- [Android Studio](https://developer.android.com/studio) (Ladybug or newer recommended)
- JDK 11 or higher
- Android SDK Platform 37

### 2. Clone the Repository
Open your terminal or command prompt and run the following command to clone the repository:

```bash
git clone https://github.com/your-username/StudentDatabase.git
```

Navigate into the project directory:

```bash
cd StudentDatabase
```

### 3. Open in Android Studio
1. Launch **Android Studio**.
2. Select **Open** (or *File > Open*).
3. Navigate to and select the cloned `StudentDatabase` folder (`/path/to/StudentDatabase`).
4. Click **OK**.

### 4. Sync Gradle
Android Studio will automatically detect the project structure and prompt you to sync Gradle. Click **Sync Now** (or go to *File > Sync Project with Gradle Files*).

### 5. Run the Application
1. Connect an Android physical device with USB Debugging enabled, or set up an **Android Virtual Device (AVD)** Emulator (API 26 or higher).
2. Click the **Run** button (green play icon ▶️) or press `Shift + F10` in Android Studio to build and launch the app.
