# **ClearScore Credit Score App**

## **Overview**
ClearScore Credit Score App is an Android application built using **Jetpack Compose** that fetches and displays a user's credit score in a **donut chart visualization**. The app is designed to be **scalable, testable, and production-grade**, following **SOLID** principles and modern Android development best practices.

This project serves as a technical submission for the **ClearScore Mobile Tech Task**, demonstrating my ability to work with **REST APIs, Jetpack Compose, MVVM architecture, dependency injection, and unit testing**.

---

## **Features**
✅ Fetches real-time **credit score** data from a given API endpoint  
✅ Displays the credit score using a **donut chart** visualization  
✅ Implements **error handling** for API failures and network issues  
✅ Provides a **responsive UI** that adapts to different screen sizes  
✅ **Unit-tested ViewModel** to ensure reliability  
✅ **Follows MVVM Architecture** for maintainability and scalability  

---

## **Tech Stack & Tools**
| **Category**         | **Technology Used** |
|----------------------|--------------------|
| **Language**        | Kotlin |
| **UI Framework**    | Jetpack Compose |
| **Architecture**    | MVVM (Model-View-ViewModel) |
| **Networking**      | Retrofit |
| **Dependency Injection** | Hilt |
| **State Management** | Kotlin Flow |
| **Testing**         | JUnit, MockK |

---

## **Project Structure**
The project follows a clean architecture approach to ensure scalability and maintainability.

```
📂 ClearScoreApp
 ├── 📂 data
 │   ├── 📂 remote            # Network operations (Retrofit API)
 │   ├── 📂 repository        # Repository pattern for data handling
 ├── 📂 domain
 │   ├── 📂 model             # Data models (Credit Score)
 │   ├── 📂 usecase           # Business logic (fetching score)
 ├── 📂 presentation
 │   ├── 📂 ui                # Jetpack Compose UI components
 │   ├── 📂 viewmodel         # Handles UI state and API calls
 ├── 📂 di                    # Hilt dependency injection
 ├── 📂 utils                 # Helper functions and extensions
 ├── 📄 build.gradle          # Project dependencies
 ├── 📄 README.md             # Project documentation
```

---

## **API Integration**
The app fetches the credit score from the provided **REST API** endpoint:

```
https://android-interview.s3.eu-west-2.amazonaws.com/endpoint.json
```

✅ **Retrofit** is used to handle network requests efficiently.  
✅ **Coroutines & Flow** ensure smooth asynchronous data fetching.  
✅ **Error Handling** manages failures gracefully (e.g., network errors, empty data).  

---

## **UI & User Experience**
The app displays the credit score using a **Donut Chart**, ensuring a clear and intuitive visualization.

🔹 **Smooth animations** using Jetpack Compose  
🔹 **Responsive design** for different screen sizes  
🔹 **Multiple UI states** (loading, success, error)  
🔹 **Accessibility support** for improved usability  

---

## **Testing Strategy**
The project follows a **test-driven development (TDD)** approach to ensure high code quality and reliability.

✔ **Unit Tests**  
- Tests ViewModel logic using **JUnit** and **MockK**  
- Verifies API calls and repository interactions  

✔ **UI Tests**  
- Simulates user interactions in Jetpack Compose  
- Uses **Compose Test Rule** to validate UI behavior  

✔ **Error Handling Tests**  
- Simulates API failures and ensures graceful fallbacks  

---

## **How to Run the Project**
1. **Clone the repository**
   ```sh
   git clone https://github.com/yourusername/ClearScoreApp.git
   cd ClearScoreApp
   ```
2. **Open the project in Android Studio** (latest version)  
3. **Build & Run the app** on an emulator or physical device  
4. Ensure you have a **stable internet connection** to fetch the credit score data  

---

## **Future Enhancements**
🚀 **Caching & Offline Support** – Store credit scores for offline viewing  
🎨 **Custom Themes & UI Enhancements** – Improve animations and UI polish  
🌍 **Localization Support** – Add support for multiple languages  
📊 **Historical Score Tracking** – Allow users to track score changes over time  

---

## **Final Thoughts**
This project demonstrates my ability to develop **scalable, testable, and maintainable** Android applications using **Jetpack Compose, MVVM, and Kotlin Coroutines**. I focused on **clean code practices, error handling, and testing** to ensure a high-quality submission.
