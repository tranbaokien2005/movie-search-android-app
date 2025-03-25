# Movie Search Android App 🎬📱

An Android application that allows users to search for movies using the OMDB API.  
Built with modern Android architecture components: **MVVM**, **LiveData**, **ViewBinding**.

---

## 🚀 Features

- 🔍 **Search movies** by title with real-time results from the OMDB API
- 📄 **Movie details** screen with poster, plot, genre, rating, and more
- 🎯 Clean architecture using **MVVM Pattern**
- 🔗 API Integration with **Retrofit**
- 🧪 Asynchronous UI updates with **LiveData**
- 💡 View management using **ViewBinding**
- 🧠 Optimized for performance and readability

---

## 📸 Screenshots

*(Thêm ảnh UI App sau nếu có – ví dụ bạn chụp màn hình giao diện Search và Details)*

---

## 🛠️ Tech Stack

| Component       | Technology           |
|----------------|----------------------|
| Language        | Java / Kotlin        |
| Architecture    | MVVM                 |
| API             | [OMDB API](http://www.omdbapi.com/) |
| Networking      | Retrofit             |
| UI Binding      | ViewBinding          |
| UI Components   | RecyclerView, CardView, etc. |

---

## 📂 Project Structure

```bash
📦 movie-search-android-app  
 ┣ 📂 data  
 ┃ ┣ 📜 MovieApiService.java  
 ┃ ┗ 📜 MovieRepository.java  
 ┣ 📂 model  
 ┃ ┗ 📜 Movie.java  
 ┣ 📂 ui  
 ┃ ┣ 📜 MainActivity.java  
 ┃ ┗ 📜 MovieDetailsActivity.java  
 ┣ 📂 viewmodel  
 ┃ ┗ 📜 MovieViewModel.java  
 ┗ 📜 AndroidManifest.xml
