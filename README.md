# Fi Thnity  (On My Way)

**Save Time, Save Tunisia** - A community-driven carpooling and ride-sharing application for Tunisia.

## 🚗 About

Fi Thnity connects Tunisians heading in the same direction, making transportation easier, faster, and more affordable. Whether you need a ride or are offering one, Fi Thnity helps you find travel companions for your journey.

## ✨ Features

- 📱 **Phone Authentication** - Secure login via Firebase Phone Auth
- 🗺️ **MapLibre Integration** - Interactive maps powered by MapTiler
- 🚖 **Ride Broadcasting** - Post ride requests or offers with transport type selection
- 📍 **Location Selection** - Interactive map-based location picker with reverse geocoding
- 👥 **Multiple Transport Types** - Taxi, Taxi Collectif, Private Car, Metro, Bus
- 🎨 **Material Design 3** - Modern, Tunisian-inspired UI with custom color palette
- 🔥 **Firebase Backend** - Realtime Database, Authentication, Cloud Messaging

## 🎨 Design

The app features a Tunisian-inspired color palette:
- **Bleu Saphir Tunisien** (#006D9C) - Primary
- **Jaune Sable du Sahel** (#FFD54F) - Secondary
- **Rouge Médina** (#D62828) - Accent

## 🛠️ Tech Stack

- **Language:** Java
- **Min SDK:** 29 (Android 10)
- **Target SDK:** 36
- **Maps:** MapLibre GL Native 10.0.2 with MapTiler
- **Backend:** Firebase (Auth, Realtime Database, Cloud Messaging)
- **UI:** Material Design 3
- **Build Tool:** Gradle 8.13

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- JDK 11 or higher
- MapTiler API Key (free tier available)
- Firebase project with google-services.json

### Setup

1. **Clone the repository**
   ```bash
   git clone git@github.com:medb2m/fi_thnity.git
   cd fi_thnity
   ```

2. **Configure API Keys**

   Copy the example gradle.properties:
   ```bash
   cp gradle.properties.example gradle.properties
   ```

   Edit `gradle.properties` and add your MapTiler API key:
   ```properties
   MAPTILER_API_KEY="your_api_key_here"
   ```

   Get your free API key from [MapTiler Cloud](https://cloud.maptiler.com/)

3. **Configure Firebase**

   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Enable Phone Authentication
   - Enable Realtime Database
   - Download `google-services.json` and place it in `app/` directory

4. **Build and Run**
   ```bash
   ./gradlew build
   ```

   Open in Android Studio and run on emulator or device.

## 📱 App Structure

### Screens
- **Splash & Onboarding** - Welcome screens with app introduction
- **Phone Authentication** - Login with phone number and OTP
- **Profile Setup** - Complete user profile
- **Home** - Map view with quick actions
- **Broadcast Ride** - Post ride request or offer
- **Rides** - Active rides list
- **Community** - Social feed
- **Profile** - User profile and settings

### Key Components
- `activities/` - All activity classes
- `fragments/` - Fragment implementations (Home, Rides, Community, Profile)
- `models/` - Data models (Ride, Location, TransportType, User)
- `adapters/` - RecyclerView adapters
- `utils/` - Utility classes

## 🔐 Security

**Important:** Never commit sensitive files to Git!

The following files are gitignored and must be configured locally:
- `gradle.properties` - Contains API keys
- `google-services.json` - Firebase configuration
- `*.keystore` - Signing keys

## 📝 Development Status

**Completed:**
- ✅ Onboarding screens
- ✅ Phone authentication
- ✅ Profile setup
- ✅ Home screen with bottom navigation
- ✅ MapLibre integration
- ✅ Location selection
- ✅ Broadcast ride screen

**In Progress:**
- 🔄 Real-time location tracking service
- 🔄 Community feed
- 🔄 Chat functionality

## 🤝 Contributing

This is a student project for ESPRIT. Contributions are welcome!

## 📄 License

This project is for educational purposes.

## 👥 Team

Developed at ESPRIT - École Supérieure Privée d'Ingénierie et de Technologies

---

** On My Way** 🇹🇳
