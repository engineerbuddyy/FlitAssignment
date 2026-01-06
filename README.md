This project demonstrates a dynamic, backend-driven dashboard screen built using Jetpack Compose and MVVM architecture.
Widgets are rendered purely based on metadata received from a backend source, without hardcoded UI positions.

📌 Problem Statement

Build a dashboard screen that:

Displays a vertical list of widgets

Renders widgets only from backend metadata

Supports multiple instances of the same widget type

Ensures widget state is isolated using instanceId

Uses Jetpack Compose and MVVM

Avoids business logic inside composables
