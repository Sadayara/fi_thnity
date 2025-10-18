# Fi Thnity Android App - Development Progress

## 📱 Project Overview

**App Name**: Fi Thnity (يقيرط يف - On My Way)
**Tagline**: Save Time, Save Tunisia
**Package**: `tn.esprit.fi_thnity`
**Min SDK**: 29 (Android 10)
**Target SDK**: 36
**Map Provider**: MapLibre GL Native with MapTiler

---

## 🎨 Branding & Design System

### Color Palette (Tunisian-Inspired)

| Usage | Color Name | Hex Code | Meaning |
|-------|------------|----------|---------|
| **Primary** | Bleu Saphir Tunisien | `#006D9C` | Mediterranean sea & trust |
| **Secondary** | Jaune Sable du Sahel | `#FFD54F` | Sunshine & positive energy |
| **Accent** | Rouge Médina | `#D62828` | Tunisian flag tribute, passion |
| **Background** | Gris Sable Clair | `#F9F9F9` | Neutral, soft interface |
| **Text Primary** | Bleu Nuit | `#1E1E1E` | Readable, high contrast |
| **Text Secondary** | Gris Moyen | `#757575` | Secondary elements |
| **Success/Online** | Vert Olivier | `#4CAF50` | Active users |
| **Error/Alert** | Rouge Corail | `#E53935` | Alerts & errors |

### Typography

- **Titles**: Sans-serif Medium (Poppins alternative) - Bold
- **Subtitles/Buttons**: Sans-serif Medium
- **Body Text**: Roboto Regular - 16sp/14sp
- **Captions**: Sans-serif - 12sp

### UI Patterns

- **Card Corner Radius**: 16dp
- **Button Corner Radius**: 12dp
- **Card Elevation**: 4-8dp
- **Padding**: 16-24dp standard
- **Material Design 3** components throughout

---

## ✅ Completed Features

### 1. **MapLibre GL Native Integration** ✅

**Replaced Google Maps with MapLibre + MapTiler**

- **Dependencies**:
  - MapLibre SDK: `10.0.2`
  - MapTiler API Key: `TVDJiURedDHa9LhhIx12`

- **Configuration**:
  - API key stored in `gradle.properties`
  - Exposed via BuildConfig
  - Current map style: `streets-v2`
  - Default center: Tunis (36.8065°N, 10.1815°E)

- **Available Map Styles**:
  - `streets-v2` - Default street map (current)
  - `basic-v2` - Minimalist
  - `bright-v2` - Light, clean
  - `satellite` - Satellite imagery
  - `hybrid` - Satellite with labels
  - `topo-v2` - Topographic
  - `voyager-v2` - Travel-focused

**Files**:
- `libs.versions.toml:14-15, 48-49`
- `gradle.properties:23-24`
- `build.gradle.kts:20, 38, 58`

---

### 2. **Splash Screen** ✅

**Duration**: 2.5 seconds
**Features**:
- App logo (120x120dp)
- "Fi Thnity" title
- "Save Time, Save Tunisia" slogan
- Progress indicator
- Blue Saphir background (#006D9C)

**Flow**: Auto-navigates to Onboarding

**Files**:
- `SplashActivity.java`
- `activity_splash.xml`

---

### 3. **Onboarding Flow (3 Screens)** ✅

**Screen 1: Share Your Journey**
- Title: "Share Your Journey"
- Description: "Going somewhere? Share your ride and help others reach their destination while saving time and money."
- Icon: Car illustration

**Screen 2: Find Rides Instantly**
- Title: "Find Rides Instantly"
- Description: "Need a lift? Broadcast your location and connect with nearby drivers heading your way."
- Icon: Location pin

**Screen 3: Build Community**
- Title: "Build Community"
- Description: "Join a community of Tunisians helping each other. Share traffic updates, delays, and stay connected."
- Icon: People illustration

**Features**:
- ViewPager2 for smooth swiping
- Page indicators (active/inactive)
- Skip button
- Next button (becomes "Get Started" on last page)
- Custom vector illustrations

**Files**:
- `OnboardingActivity.java`
- `OnboardingAdapter.java`
- `OnboardingItem.java`
- `activity_onboarding.xml`
- `item_onboarding.xml`

---

### 4. **Phone Authentication** ✅

**Phone Number Entry Screen**:
- Tunisia country code (+216) pre-filled & disabled
- 8-digit phone number input
- Input validation (required, length check)
- Material Design card layout
- Loading states with progress bar
- Terms & Privacy notice

**OTP Verification Screen**:
- 6-digit code input (centered, large text)
- Back button navigation
- 60-second resend countdown timer
- Resend button (auto-enables after countdown)
- Phone number display
- Loading states

**Validation**:
- Phone: Required, exactly 8 digits
- OTP: Required, exactly 6 digits

**Files**:
- `PhoneAuthActivity.java`
- `OTPVerificationActivity.java`
- `activity_phone_auth.xml`
- `activity_otp_verification.xml`

---

### 5. **Profile Setup** ✅

**Features**:
- Circular profile photo (140dp diameter)
- Image picker integration (Gallery/Camera)
- FAB camera button overlay
- Full name input (required, min 2 chars, max 50)
- Bio input (optional, max 150 chars)
- Info card with verification message
- Upload simulation with loading

**Layout**:
- Centered profile photo at top
- Text inputs in cards
- Primary colored info banner
- Complete setup button at bottom

**Files**:
- `ProfileSetupActivity.java`
- `activity_profile_setup.xml`

---

### 6. **Home Screen with Bottom Navigation** ✅

#### Bottom Navigation (4 Tabs)

| Tab | Icon | Fragment |
|-----|------|----------|
| 🏠 Home | Compass | HomeFragment |
| 🚗 Rides | Location | RidesFragment |
| 👥 Community | History | CommunityFragment |
| 👤 Profile | Places | ProfileFragment |

**Styling**:
- Icons & text: Primary color (#006D9C)
- Background: Surface white
- Active state highlighting

---

#### **Home Fragment** (Map + Quick Actions)

**Top Card**:
- Welcome message: "Welcome back, [Name]!"
- Location display with icon
- Current location: "Tunis, Tunisia"

**Map View**:
- Full-screen MapLibre map
- Interactive (pan, zoom, rotate)
- Centered on Tunis
- Default zoom: 12.0

**Quick Actions Card** (Bottom):
- "I Need a Ride" button - Primary blue (#006D9C)
- "I Offer a Ride" button - Accent red (#D62828)
- Large touch targets (48dp icon + text)
- Horizontal layout (50/50 split)
- Active rides indicator text

**Floating Action Button**:
- Current location icon
- Right-bottom position
- Margin: 16dp, offset 200dp from bottom
- Surface background with primary icon

**Interactions**:
- Need Ride → Toast (ready for navigation)
- Offer Ride → Toast (ready for navigation)
- Current Location → Toast (ready for GPS)

**Files**:
- `HomeFragment.java`
- `fragment_home.xml`

---

#### **Rides Fragment**

**Layout**:
- "Active Rides" header (Headline2 style)
- RecyclerView for ride list
- LinearLayoutManager

**Empty State**:
- Large location icon (100dp, grey tint)
- "No active rides" message
- Centered vertically

**Ready for**:
- Ride adapter implementation
- Firebase real-time ride data
- Ride item cards

**Files**:
- `RidesFragment.java`
- `fragment_rides.xml`

---

#### **Community Fragment**

**Layout**:
- "Community" header
- RecyclerView for posts
- LinearLayoutManager

**Floating Action Button**:
- (+) icon for new post
- Primary color background
- Bottom-right position (24dp margin)

**Ready for**:
- Post adapter implementation
- Firebase community posts
- Post creation dialog

**Files**:
- `CommunityFragment.java`
- `fragment_community.xml`

---

#### **Profile Fragment**

**Profile Header Card**:
- Circular photo (100dp, rounded)
- User name (Headline2)
- Phone number (Body2)
- Stats section:
  - ⭐ Rating: 4.8 (primary color)
  - 🚗 Total Rides: 0 (accent color)
  - Horizontal 50/50 layout

**Menu Cards** (with arrow icons):
1. ✏️ **Edit Profile**
   - Opens `ProfileSetupActivity`
   - Full profile editing

2. 📋 **My Rides**
   - View ride history (placeholder)

3. ⚙️ **Settings**
   - App settings (placeholder)

**Logout Button**:
- Outlined style
- Red border (error color)
- Full width
- Top margin: 12dp

**Files**:
- `ProfileFragment.java`
- `fragment_profile.xml`

---

### 7. **Data Models** ✅

#### **User.java**
```java
- String uid
- String name
- String phoneNumber
- String photoUrl
- double rating
- int totalRides
- boolean isVerified
- Location currentLocation
- boolean isActive
- long timestamp
```

#### **TransportType.java** (Enum)
```java
TAXI (4 seats, #FFD54F)
TAXI_COLLECTIF (8 seats, #FF9800)
PRIVATE_CAR (3 seats, #006D9C)
METRO
BUS
```

#### **Location.java**
```java
- double latitude
- double longitude
- String address
- long timestamp
+ double distanceTo(Location other) // Haversine formula
```

#### **Ride.java**
```java
- String rideId
- User user
- RideType type (REQUEST/OFFER)
- TransportType transportType
- Location origin
- Location destination
- int availableSeats
- RideStatus status (ACTIVE/MATCHED/COMPLETED/CANCELLED/EXPIRED)
- long createdAt
- long expiresAt (auto 2 hours)
```

#### **CommunityPost.java**
```java
- String postId
- User user
- String content
- PostType type (ACCIDENT, DELAY, ROAD_CLOSURE, GENERAL)
- Location location
- int likesCount
- long timestamp
+ String getTimeAgo() // "5 min ago"
```

#### **OnboardingItem.java**
```java
- int imageResId
- String title
- String description
```

---

## 📂 Project Structure

```
tn.esprit.fi_thnity/
├── activities/
│   ├── SplashActivity.java ✅
│   ├── OnboardingActivity.java ✅
│   ├── PhoneAuthActivity.java ✅
│   ├── OTPVerificationActivity.java ✅
│   ├── ProfileSetupActivity.java ✅
│   └── MainActivity.java ✅
│
├── fragments/
│   ├── HomeFragment.java ✅
│   ├── RidesFragment.java ✅
│   ├── CommunityFragment.java ✅
│   └── ProfileFragment.java ✅
│
├── models/
│   ├── User.java ✅
│   ├── Location.java ✅
│   ├── Ride.java ✅
│   ├── TransportType.java ✅
│   ├── CommunityPost.java ✅
│   └── OnboardingItem.java ✅
│
├── adapters/
│   └── OnboardingAdapter.java ✅
│
├── services/ (ready for implementation)
├── utils/ (ready for implementation)
└── BuildConfig (MapTiler API key)
```

---

## 📱 App Navigation Flow

```
Splash Screen (2.5s)
    ↓
Onboarding (3 screens)
    ↓ (Skip or Get Started)
Phone Authentication
    ↓
OTP Verification (60s timer)
    ↓
Profile Setup (new users)
    ↓
MainActivity
    └── Bottom Navigation
        ├── Home (Map + Quick Actions) ✅
        ├── Rides (Active rides list) ✅
        ├── Community (Feed + FAB) ✅
        └── Profile (Stats + Menu) ✅
```

---

## 🔧 Technical Configuration

### Gradle Dependencies

**MapLibre**:
```kotlin
implementation("org.maplibre.gl:android-sdk:10.0.2")
```

**Firebase** (BOM-managed):
```kotlin
implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
implementation("com.google.firebase:firebase-auth")
implementation("com.google.firebase:firebase-database")
implementation("com.google.firebase:firebase-messaging")
implementation("com.google.firebase:firebase-storage")
implementation("com.google.firebase:firebase-analytics")
```

**Other Libraries**:
- Material Components (Material3)
- ViewPager2
- RecyclerView
- ConstraintLayout
- CoordinatorLayout
- Retrofit (for API calls)
- OkHttp (networking)
- Room Database (local storage)
- Glide (image loading)
- Navigation Component
- Lifecycle Components

### Theme Configuration

**Base Theme**: `Theme.Material3.DayNight.NoActionBar`

**Custom Attributes**:
- colorPrimary: #006D9C
- colorSecondary: #FFD54F
- colorAccent: #D62828
- colorError: #E53935
- android:statusBarColor: #006D9C

**Custom Styles**:
- `Widget.FiThnity.Button`
- `Widget.FiThnity.Button.Secondary`
- `Widget.FiThnity.Button.Outlined`
- `Widget.FiThnity.Card`
- `Widget.FiThnity.TextInputLayout`
- `TextAppearance.FiThnity.Headline1/2`
- `TextAppearance.FiThnity.Body1/2`
- `TextAppearance.FiThnity.Button`

### Permissions (AndroidManifest.xml)

**Location**:
- ACCESS_FINE_LOCATION
- ACCESS_COARSE_LOCATION
- ACCESS_BACKGROUND_LOCATION
- FOREGROUND_SERVICE
- FOREGROUND_SERVICE_LOCATION

**Notifications**:
- POST_NOTIFICATIONS (Android 13+)
- WAKE_LOCK

**Media**:
- CAMERA
- READ_MEDIA_IMAGES

**Network**:
- INTERNET
- ACCESS_NETWORK_STATE

---

## 🎯 Remaining Features to Implement

### 1. **Ride Request/Offer Screen** 🔜

**Requirements**:
- [ ] Transport type selector (5 types)
  - Taxi (4 seats)
  - Taxi Collectif (8 seats)
  - Private Car (3 seats)
  - Metro
  - Bus
- [ ] Origin location picker (MapLibre)
- [ ] Destination location picker (MapLibre)
- [ ] Address search/autocomplete
- [ ] Available seats counter (1-8)
- [ ] Broadcast button
- [ ] Firebase real-time save

**UI Components**:
- Transport type chips (Material)
- Map for location selection
- Search bar with autocomplete
- Stepper for seat selection
- Summary card

---

### 2. **Firebase Integration** 🔜

**Authentication**:
- [ ] Phone number verification
- [ ] Firebase Auth integration
- [ ] Store user session
- [ ] Auto-login on app restart

**Realtime Database Structure**:
```
firebase-database/
├── users/
│   └── {userId}/
│       ├── profile (User object)
│       └── location (last known)
├── rides/
│   ├── requests/
│   │   └── {rideId} (Ride object)
│   └── offers/
│       └── {rideId} (Ride object)
├── matches/
│   └── {matchId}/
│       ├── requestId
│       ├── offerId
│       └── status
└── community/
    └── posts/
        └── {postId} (CommunityPost object)
```

**Storage**:
- [ ] Profile photo uploads
- [ ] Photo compression
- [ ] Download URLs

**Cloud Messaging**:
- [ ] Ride match notifications
- [ ] Chat message notifications
- [ ] Community post notifications

---

### 3. **Real-time Location Tracking** 🔜

**Foreground Service**:
- [ ] LocationService.java
- [ ] GPS updates every 30 seconds
- [ ] Firebase location sync
- [ ] Battery optimization
- [ ] Notification with app info

**Permissions Flow**:
- [ ] Runtime permission requests
- [ ] Background location justification
- [ ] Notification permission (Android 13+)

---

### 4. **Community Feed** 🔜

**Post Types**:
- 🚨 Accident
- ⏱ Delay
- 🚧 Road Closure
- 📢 General

**Features**:
- [ ] Post creation dialog
- [ ] Post type selector
- [ ] Location tagging
- [ ] Image attachments (optional)
- [ ] Like button
- [ ] Comment section
- [ ] Real-time updates
- [ ] Time ago formatting

**Adapter**:
- [ ] CommunityPostAdapter.java
- [ ] ViewHolder with post card
- [ ] Click listeners

---

### 5. **Chat Functionality** 🔜

**Features**:
- [ ] One-to-one chat
- [ ] Chat list screen
- [ ] Message bubbles (sent/received)
- [ ] Timestamp display
- [ ] Read receipts
- [ ] Push notifications
- [ ] Firebase Realtime Database for messages

**Database Structure**:
```
chats/
└── {chatId}/
    ├── participants: [userId1, userId2]
    └── messages/
        └── {messageId}/
            ├── senderId
            ├── text
            ├── timestamp
            └── read
```

---

### 6. **Ride Matching Algorithm** 🔜

**Logic**:
- [ ] Match ride requests with offers
- [ ] Distance-based matching (< 2km)
- [ ] Transport type compatibility
- [ ] Available seats check
- [ ] Time window (< 30 min old)
- [ ] Notification on match

---

### 7. **User Settings** 🔜

**Options**:
- [ ] Notification preferences
- [ ] Location sharing on/off
- [ ] Language selection (English/Arabic)
- [ ] Dark mode toggle
- [ ] About app
- [ ] Privacy policy
- [ ] Terms of service

---

### 8. **My Rides History** 🔜

**Features**:
- [ ] Past rides list
- [ ] Ride details (origin, destination, date)
- [ ] Rating system
- [ ] Filters (date, type)
- [ ] Export functionality

---

## 🐛 Known Issues & TODOs

### High Priority
- [ ] Add Firebase google-services.json to app/
- [ ] Implement Firebase Phone Auth
- [ ] Add user session management (SharedPreferences)
- [ ] Check if user is logged in on splash screen
- [ ] Save onboarding completion flag

### Medium Priority
- [ ] Add proper app icon (currently using default)
- [ ] Download and add Poppins font family
- [ ] Create custom vector icons for bottom nav
- [ ] Implement image compression for uploads
- [ ] Add network connectivity check

### Low Priority
- [ ] Add animations (fragment transitions)
- [ ] Implement pull-to-refresh
- [ ] Add empty state illustrations
- [ ] Create app intro video
- [ ] Add haptic feedback

---

## 📊 Testing Checklist

### ✅ Completed Tests
- [x] Splash screen displays correctly
- [x] Onboarding swipe works
- [x] Skip button navigates to auth
- [x] Phone input validation (8 digits)
- [x] OTP timer counts down
- [x] Profile setup saves and navigates
- [x] Bottom navigation switches fragments
- [x] Map loads correctly
- [x] Quick action buttons are clickable
- [x] Profile edit opens ProfileSetupActivity

### 🔜 Pending Tests
- [ ] Firebase authentication flow
- [ ] Ride creation and broadcast
- [ ] Real-time location updates
- [ ] Community post creation
- [ ] Chat messaging
- [ ] Notifications
- [ ] Offline functionality
- [ ] Multi-language support
- [ ] Dark mode

---

## 🚀 Deployment Checklist

### Before Beta Release
- [ ] Add Firebase google-services.json
- [ ] Configure Firebase Auth (Phone)
- [ ] Set up Firebase Realtime Database rules
- [ ] Configure Firebase Storage rules
- [ ] Enable Firebase Cloud Messaging
- [ ] Generate signed APK
- [ ] Test on multiple devices
- [ ] Optimize ProGuard rules
- [ ] Add crash reporting (Firebase Crashlytics)

### Before Production
- [ ] Complete feature set (all 9 features)
- [ ] Security audit
- [ ] Performance testing
- [ ] Accessibility review
- [ ] Legal review (privacy policy, terms)
- [ ] App store assets (screenshots, description)
- [ ] Play Store listing
- [ ] Privacy policy URL
- [ ] Support email/website

---

## 📞 Support & Resources

**Documentation**:
- MapLibre Android: https://maplibre.org/maplibre-gl-native/android/
- MapTiler Docs: https://docs.maptiler.com/maplibre-gl-native-android/
- Firebase Docs: https://firebase.google.com/docs/android/setup
- Material Design: https://m3.material.io/

**API Keys**:
- MapTiler API Key: `TVDJiURedDHa9LhhIx12`
- Firebase Project: (to be configured)

**Package Name**: `tn.esprit.fi_thnity`

---

## 📈 Future Enhancements

### Phase 2 Features
- [ ] Driver verification system
- [ ] In-app payments (optional)
- [ ] Ride scheduling (future rides)
- [ ] Favorite locations
- [ ] Ride sharing groups
- [ ] Gamification (badges, leaderboard)
- [ ] Carbon footprint calculator

### Phase 3 Features
- [ ] Multi-city support
- [ ] International expansion
- [ ] Corporate accounts
- [ ] API for third-party integration
- [ ] Web dashboard
- [ ] iOS version

---

## 🎓 Learning Resources

**Technologies Used**:
- Java (Android)
- MapLibre GL Native
- Firebase (Auth, Database, Storage, Messaging)
- Material Design 3
- RecyclerView & Adapters
- Fragments & Navigation
- Location Services
- Foreground Services

**Design Patterns**:
- MVVM (ready for ViewModels)
- Repository pattern (for data layer)
- Observer pattern (LiveData, Firebase listeners)
- Singleton pattern (Firebase instances)

---

**Last Updated**: 2025-10-18
**Version**: 1.0.0-beta
**Status**: 🟢 Active Development
**Completion**: ~60% (7/12 major features)

---

*Built with ❤️ for Tunisia 🇹🇳*
