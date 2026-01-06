This project demonstrates a dynamic, backend-driven dashboard screen built using Jetpack Compose and MVVM architecture.
Widgets are rendered purely based on metadata received from a backend source, without hardcoded UI positions.

📌 Problem Statement

Build a dashboard screen that:

Displays a vertical list of widgets

Renders widgets only from backend metadata

Supports multiple instances of the same widget type

Ensures widget state is isolated using instanceId

Uses Jetpack Compose and MVVM

Avoids business logic inside composables.


📌Architecture Overview

UI (Compose)

   ↓
 
ViewModel

   ↓
 
Domain (Repository Interface, Models)'

   ↓
 
Data (Repository Implementation, Fake APIs)



📌 Key Design Principles


UI is stateless and renders data only

ViewModels own all logic and state

Repositories abstract data sources

Each widget instance is independent


🔄 Data Flow

DashboardViewModel fetches widget metadata from the repository

DashboardScreen renders widgets dynamically using this metadata

For each widget:

Banner widgets receive static data and render immediately

List widgets create their own ViewModel using instanceId

Each List widget:

Fetches data independently

Manages its own loading / success / error state

