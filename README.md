# 🎬 Movie Search Android App

An Android app for searching movies using the OMDB API. Built using **MVVM Architecture**, **LiveData**, **ViewBinding**, and **Retrofit**.

---

## 📱 Features

- 🔍 Search for movies by title using OMDB API
- 📄 View detailed information for each movie
- ⚙️ User-friendly UI with clean design and smooth navigation
- 🧱 MVVM Architecture with ViewModel and LiveData
- 🚀 Optimized using Retrofit for networking and Picasso for image loading

---

## 🛠️ Tech Stack

| Category         | Technology                                      |
|------------------|--------------------------------------------------|
| Language         | Java                                             |
| Architecture     | MVVM                                             |
| Networking       | Retrofit                                         |
| UI Components    | RecyclerView, ViewBinding                       |
| Image Loading    | Picasso                                          |
| API              | [OMDB API](https://www.omdbapi.com/)            |
| Others           | Android SDK, Gradle                             |

---

## 📸 Screenshots

### 🔍 Movie Search Screen  
![Search Screen](https://github.com/user-attachments/assets/22d33935-6333-42cd-86f5-44420530528f)

### 🎬 Movie Details Screen  
![Details Screen](https://github.com/user-attachments/assets/d5716416-6422-4b09-b2dc-9fdd3b12e8e2)

---

## 🚀 Getting Started

### ▶️ Run Locally

1. Clone this repository
- Open your terminal and run the following command:
  git clone https://github.com/tranbaokien2005/movie-search-android-app.git

2. Open project in Android Studio
- Select "Open an existing project"
- Navigate to the folder you just cloned

3. Add your OMDB API key
- Open the RetrofitClient.java file
- Replace "YOUR_API_KEY" with your actual OMDB API key:
  private static final String API_KEY = "YOUR_API_KEY";

4. Build & Run the app
- Choose an emulator or connect a physical device
- Press the Run ▶️ button to launch the app

---

## 🧠 Folder Structure
com.example.assigntwo
├── model # Movie model classes
├── network # API services using Retrofit
├── ui # UI (Activities, Adapter)
├── viewmodel # ViewModel layer
├── res/layout # XML layouts
├── res/values # colors, strings, themes

---

## 📌 Project Status

✅ Completed basic features  
🎯 Future plans: Add search history, favorites, dark mode

---

## 🙋‍♂️ Author

**Tran Bao Kien**  
📧 tranbaokien.2005@gmail.com  
🔗 [LinkedIn](www.linkedin.com/in/bao-kien-tran) | [GitHub](https://github.com/tranbaokien2005)

---

## 📄 License

This project is for educational purposes. No license applied.


---
