# Project Plan

Migrate the existing Android project 'KalSeAajTakNews' to a Kotlin Multiplatform (KMP) project. 
The migration should follow Clean Architecture (Data, Domain, UI), utilize KMP-compatible libraries (Ktor, Room KMP, Coil KMP), and use Compose Multiplatform for shared UI. 
The app should support Android and iOS. 
Maintain the existing project's structure of ViewModels with a single UiState and UDF.

## Project Brief

# Project Brief: NewsAppKMP (Migration)

This project involves migrating the 'KalSeAajTakNews' Android application to a Kotlin Multiplatform (KMP) architecture, enabling a unified codebase for both Android and iOS while leveraging modern declarative UI and state-driven navigation.

### Features
*   **Cross-Platform News Feed:** A unified UI to browse and read news articles from various sources on both Android and iOS devices.
*   **Adaptive Layouts:** Responsive user interface that automatically adjusts for different screen sizes (phones, foldables, and tablets) using Material 3 adaptive patterns.
*   **Offline Access:** Local caching of news articles to ensure a seamless reading experience even without an active internet connection.
*   **State-Driven Navigation:** Robust and predictable navigation flow between the news feed and article details, managed via centralized state.

### High-Level Technical Stack
*   **Kotlin Multiplatform (KMP):** Shared business logic, data models, and networking across platforms.
*   **Compose Multiplatform:** A shared UI framework to build the visual layer for both Android and iOS using a single codebase.
*   **Jetpack Navigation 3:** A state-driven navigation approach to handle transitions and deep linking across the multi-platform app.
*   **Compose Material Adaptive:** Utilized for building layouts that respond to different window size classes and device postures.
*   **Ktor:** A lightweight, multiplatform asynchronous HTTP client for fetching news data from REST APIs.
*   **Room KMP:** For cross-platform local data persistence and caching.
*   **Kotlin Coroutines & Flow:** For managing asynchronous tasks and reactive data streams with a Unidirectional Data Flow (UDF) pattern.
*   **Coil (KMP):** Image loading and caching for shared UI components.

## Implementation Steps

### Task_1_SetupKMPStructure: Convert the project to a Kotlin Multiplatform structure. This involves updating the root and app-level build.gradle.kts files, configuring source sets (commonMain, androidMain, iosMain), and updating libs.versions.toml with KMP-compatible libraries like Ktor, Room KMP, and Compose Multiplatform.
- **Status:** COMPLETED
- **Updates:** Converted the project to a KMP structure. 
- Created :shared module with commonMain, androidMain, and iosMain source sets.
- Updated libs.versions.toml with KMP-compatible versions (Compose Multiplatform 1.10.3, Ktor 3.4.3, Room 2.8.4, Coil 3.4.0).
- Configured :app as a thin Android wrapper around the shared module.
- Migrated core UI entry point and theme to shared module.
- Build scripts sync successfully.
- **Acceptance Criteria:**
  - Project structure follows KMP conventions
  - Build scripts sync successfully
  - Shared module is created and linked
- **Duration:** N/A

### Task_2_MigrateDataAndDomain: Migrate the Data and Domain layers to the shared module. Replace Retrofit/Moshi with Ktor and Kotlinx Serialization. Implement Room KMP for local persistence. Move repository interfaces and implementations to commonMain to ensure business logic is shared.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Ktor client configured for commonMain
  - Room KMP database accessible across platforms
  - Data models and repositories migrated to commonMain
- **StartTime:** 2026-05-11 16:27:57 IST

### Task_3_MigrateUIAndNavigation: Migrate the UI layer to Compose Multiplatform in the shared module. Port existing Compose screens, integrate Navigation 3 for shared navigation logic, and move ViewModels to commonMain using shared Coroutine scopes and UDF pattern. Integrate Coil KMP for image loading.
- **Status:** PENDING
- **Acceptance Criteria:**
  - UI screens visible in commonMain
  - Navigation 3 handles transitions in shared code
  - Shared ViewModels maintain state correctly
  - Coil KMP loads images successfully

### Task_4_RunAndVerify: Finalize the iOS target configuration, integrate the shared module into the Android app, and verify the application. Perform testing on both Android and iOS to ensure feature parity, stability, and adherence to Material 3 design guidelines.
- **Status:** PENDING
- **Acceptance Criteria:**
  - App builds and runs on Android emulator/device
  - App builds and runs on iOS simulator/device
  - No crashes during navigation or data fetching
  - Existing tests pass or are migrated
  - UI follows Material 3 and adaptive layout principles

