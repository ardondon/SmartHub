# SmartHub - Multi-Cloud Smart Home Gateway

<div align="center">

![SmartHub Logo](images/logo.png)

**One Gateway, All Smart Home Platforms**

> ⚠️ **Project Status**: This project is currently in the planning phase. Documentation is complete, but code development has not yet started. Stay tuned or contribute!

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/status-Planning-yellow.svg)](https://github.com/ardondon/SmartHub)
[![Android](https://img.shields.io/badge/Android-8.0%2B-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-purple.svg)](https://kotlinlang.org)
[![GitHub Stars](https://img.shields.io/github/stars/ardondon/SmartHub.svg)](https://github.com/ardondon/SmartHub/stargazers)

English | [简体中文](../README.md)

</div>

---

## 📖 About

SmartHub is a **multi-cloud smart home unified gateway** based on Android tablets, allowing you to manage all smart devices from different cloud platforms with one device.

### 🌟 Key Features

- 🌐 **Multi-Cloud Integration** - Connect to 10+ platforms including Tencent IoT, Tmall Genie, Xiaomi IoT, Huawei HiLink
- 📱 **Large Touch Screen** - 8-10 inch HD touchscreen, perfect home control center
- 💰 **Best Value** - Using refurbished Android tablets, costs only 1/6 of traditional gateways
- 🔒 **Privacy First** - Pure local architecture, no data uploaded to third-party servers
- 🔓 **Open Source** - Core code open source, plugin ecosystem open
- ⚡ **Offline Available** - Control local devices even without internet
- 🎨 **Modern UI** - Smooth interface based on Jetpack Compose

### 💡 Why SmartHub?

| Feature | SmartHub | Xiaomi Gateway | Home Assistant |
|---------|----------|----------------|----------------|
| **Multi-Cloud** | ✅ 10+ | ❌ Xiaomi only | ✅ Need config |
| **Touch Screen** | ✅ 8-10" | ❌ No screen | 🔶 Need external |
| **Cost** | $30-70 | $35 | $150+ |
| **Privacy** | ✅ Local | 🔶 Cloud | ✅ Local |
| **Ease of Use** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |

---

## 🚀 Quick Start

### Hardware Preparation

Recommended refurbished Android tablets (available on eBay/AliExpress):

| Model | Screen | Specs | Price | Rating |
|-------|--------|-------|-------|--------|
| Xiaomi Pad 4 | 8" | 4GB/64GB | $30 | ⭐⭐⭐⭐ |
| Xiaomi Pad 5 | 11" | 6GB/128GB | $70 | ⭐⭐⭐⭐⭐ |
| Huawei MatePad | 10.4" | 6GB/128GB | $60 | ⭐⭐⭐⭐⭐ |
| Honor Tab 5 | 8" | 3GB/32GB | $25 | ⭐⭐⭐ |

**Minimum Requirements**: Android 8.0+, 3GB RAM, 32GB Storage

### Installation

#### Method 1: Download APK (Recommended)

1. Go to [Releases](https://github.com/ardondon/SmartHub/releases) page
2. Download latest `SmartHub-vX.X.X.apk`
3. Install APK on your tablet
4. Open app and follow setup wizard

#### Method 2: Build from Source

```bash
# Clone repository
git clone https://github.com/ardondon/SmartHub.git
cd SmartHub

# Build (requires Android Studio or Gradle)
./gradlew assembleRelease

# Install to device
adb install app/build/outputs/apk/release/app-release.apk
```

### Initial Setup

1. **Connect WiFi** - Connect tablet to your home WiFi
2. **Add Cloud Accounts** - Login to your smart home platforms
3. **Sync Devices** - Automatically discover and import all devices
4. **Create Scenes** - Setup cross-platform automation scenes
5. **Done** 🎉 - Enjoy unified smart home experience

---

## 🏗️ Architecture

### Architecture Diagram

```
┌─────────────────────────────────────┐
│     SmartHub Android App            │
│   (Running on Refurb Tablet)        │
├─────────────────────────────────────┤
│  UI Layer - Jetpack Compose         │
├─────────────────────────────────────┤
│  Business Layer - ViewModel + Use   │
├─────────────────────────────────────┤
│  Data Layer                         │
│  ├─ Room DB (Device/Scene/Config)  │
│  ├─ DataStore (Preferences)        │
│  └─ Memory Cache                   │
├─────────────────────────────────────┤
│  Cloud Platform Plugin Layer        │
│  ├─ Tencent Plugin ──→ Tencent API │
│  ├─ Tmall Plugin ──→ Alibaba API   │
│  ├─ Xiaomi Plugin ──→ Xiaomi API   │
│  ├─ Huawei Plugin ──→ Huawei API   │
│  └─ More platforms...              │
└─────────────────────────────────────┘
         ↓           ↓           ↓
    [WiFi Dev]  [BLE Dev]  [Cloud Dev]
```

### Tech Stack

**Core Framework**
- Language: Kotlin
- UI: Jetpack Compose + Material 3
- Architecture: MVVM + Clean Architecture
- Async: Coroutines + Flow

**Data Storage**
- Database: Room + SQLite
- Config: DataStore
- Cache: Memory + Persistent

**Network**
- HTTP: OkHttp + Retrofit
- MQTT: Eclipse Paho
- WebSocket: OkHttp

**Cloud Integration**
- Official SDKs for each platform
- Unified plugin interface
- Hot-swap architecture

---

## 🎯 Supported Platforms

| Platform | Status | Version | Notes |
|----------|--------|---------|-------|
| Tencent IoT | 📅 Planned | - | MVP Phase Priority |
| Tmall Genie | 📅 Planned | - | MVP Phase Priority |
| Xiaomi IoT | 📅 Planned | - | MVP Phase |
| Huawei HiLink | 📅 Planned | - | Q2 2025 |
| Baidu Cloud | 📅 Planned | - | Q3 2025 |
| JD IoT | 📅 Planned | - | Q3 2025 |
| Tuya Smart | 📅 Planned | - | Q4 2025 |
| AWS IoT | 📅 Planned | - | 2026 |
| Google Home | 📅 Planned | - | 2026 |

> 💡 **Developer Contribution**: Welcome to submit new platform plugins! Check [Plugin Development Guide](docs/plugin-development.md)

---

## 🛠️ Development

### Prerequisites

**Required Tools**
- Android Studio Hedgehog (2023.1.1) or higher
- JDK 17
- Android SDK (API 26+)
- Git

### Build

```bash
# 1. Clone project
git clone https://github.com/ardondon/SmartHub.git
cd SmartHub

# 2. Install dependencies
./gradlew build

# 3. Run tests
./gradlew test

# 4. Build debug version
./gradlew assembleDebug

# 5. Build release version
./gradlew assembleRelease
```

### Code Style

We follow [Kotlin Official Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html).

### Commit Convention

Use [Conventional Commits](https://www.conventionalcommits.org/):

```bash
feat(device): add batch device control
fix(mqtt): fix reconnection issue
docs(api): update API documentation
refactor(scene): refactor scene engine
test(platform): add Tencent unit tests
```

---

## 🤝 Contributing

We welcome all forms of contributions!

### How to Contribute

1. **Fork** this repository
2. **Create Branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit Changes** (`git commit -m 'feat: Add some AmazingFeature'`)
4. **Push Branch** (`git push origin feature/AmazingFeature`)
5. **Submit Pull Request**

See [CONTRIBUTING.md](CONTRIBUTING.md) for detailed guidelines.

---

## 📅 Roadmap

### Phase 1: MVP Development (📅 Planned - 2025 Q1-Q2)
- 📋 Android basic framework
- 📋 Tencent, Tmall integration
- 📋 Device management, scene automation
- 📋 Basic UI

### Phase 2: Beta & Optimization (📅 Planned - 2025 Q3-Q4)
- 📋 More cloud platforms (Huawei, Baidu, JD)
- 📋 AI scene recommendation
- 📋 Multi-gateway coordination
- 📋 Plugin marketplace

### Phase 3: Scale Up (📅 Planned - 2026)
- 📋 Enterprise features
- 📋 Performance optimization (100+ devices)
- 📋 Internationalization
- 📋 Developer ecosystem

---

## 📊 Project Status

![GitHub commit activity](https://img.shields.io/github/commit-activity/m/ardondon/SmartHub)
![GitHub issues](https://img.shields.io/github/issues/ardondon/SmartHub)
![GitHub pull requests](https://img.shields.io/github/issues-pr/ardondon/SmartHub)

**Project Status**: 📋 Planning Phase  
**Current Version**: v0.0.0 (Not Released)  
**Progress**: Documentation completed, code development pending  
**Expected First Release**: 2025 Q2

---

## 💬 Community & Support

### Get Help

- 📖 [Documentation](docs/user-guide.md)
- 💡 [FAQ](docs/FAQ.md)
- 🐛 [Issue Tracker](https://github.com/ardondon/SmartHub/issues)
- 💬 [Discussions](https://github.com/ardondon/SmartHub/discussions)

---

## 📄 License

This project is licensed under [Apache License 2.0](LICENSE).

```
Copyright 2024 SmartHub Team

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

## 🙏 Acknowledgments

Thanks to the following open source projects:

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin](https://kotlinlang.org/)
- [Eclipse Paho](https://www.eclipse.org/paho/)
- [OkHttp](https://square.github.io/okhttp/)
- [Room](https://developer.android.com/training/data-storage/room)
- All contributors and supporters ❤️

---

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=ardondon/SmartHub&type=Date)](https://star-history.com/#ardondon/SmartHub&Date)

---

<div align="center">

**If this project helps you, please give us a ⭐ Star!**

Made with ❤️ by SmartHub Team

[Website](https://smarthub.io) · [Docs](https://docs.smarthub.io) · [Blog](https://blog.smarthub.io)

</div>
