# SmartHub - 多云智能家居统一网关

<div align="center">

[![GitHub Stars](https://img.shields.io/github/stars/ardondon/SmartHub.svg)](https://github.com/ardondon/SmartHub/stargazers)

[English](docs/README_EN.md) | 简体中文

</div>

---

## 📖 项目简介

SmartHub 是一个基于 Android 平板的**多云智能家居统一网关**，让你用一个设备同时管理来自不同云平台的所有智能设备。

### 🌟 核心特性

- 🌐 **多云平台统一** - 同时接入腾讯连连、天猫精灵、小米IoT、华为HiLink等10+云平台
- 📱 **大屏触控体验** - 8-10寸高清触摸屏，完美的家庭控制中心
- 💰 **极致性价比** - 利用二手Android平板，成本仅为传统网关的1/6
- 🔒 **隐私安全** - 纯本地架构，数据不上传到第三方服务器
- 🔓 **开源开放** - 核心代码开源，插件生态开放
- ⚡ **离线可用** - 无网络也能控制本地设备
- 🎨 **现代化UI** - 基于 Jetpack Compose 的流畅界面

### 💡 为什么选择 SmartHub？

| 对比项 | SmartHub | 小米多模网关 | Home Assistant |
|--------|----------|--------------|----------------|
| **多云平台** | ✅ 10+ | ❌ 仅小米 | ✅ 需配置 |
| **大屏触控** | ✅ 8-10寸 | ❌ 无屏幕 | 🔶 需外接 |
| **成本** | ¥199-499 | ¥229 | $149+ (¥1,000+) |
| **隐私保护** | ✅ 纯本地 | 🔶 云端 | ✅ 本地 |
| **易用性** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |

---

## 🚀 快速开始

### 硬件准备

推荐使用以下二手 Android 平板（闲鱼/拼多多可购买）：

| 型号 | 屏幕 | 配置 | 参考价格 | 推荐度 |
|------|------|------|----------|--------|
| 小米平板4 | 8寸 | 4GB/64GB | ¥180 | ⭐⭐⭐⭐ |
| 小米平板5 | 11寸 | 6GB/128GB | ¥500 | ⭐⭐⭐⭐⭐ |
| 华为MatePad | 10.4寸 | 6GB/128GB | ¥400 | ⭐⭐⭐⭐⭐ |
| 荣耀平板5 | 8寸 | 3GB/32GB | ¥150 | ⭐⭐⭐ |

**最低要求**：Android 8.0+、3GB RAM、32GB 存储

### 安装方式

#### 方式1：下载安装包（推荐）

1. 前往 [Releases](https://github.com/ardondon/SmartHub/releases) 页面
2. 下载最新版本的 `SmartHub-vX.X.X.apk`
3. 在平板上安装 APK
4. 打开应用，按照引导配置

#### 方式2：从源码编译

```bash
# 克隆仓库
git clone https://github.com/ardondon/SmartHub.git
cd SmartHub

# 编译（需要 Android Studio 或 Gradle）
./gradlew assembleRelease

# 安装到设备
adb install app/build/outputs/apk/release/app-release.apk
```

### 首次配置

1. **连接 WiFi** - 确保平板连接到家庭 WiFi
2. **添加云平台账号** - 登录你使用的智能家居云平台
3. **同步设备** - 自动发现并导入所有设备
4. **创建场景** - 设置跨平台自动化场景
5. **完成** 🎉 - 开始享受统一的智能家居体验

---

## 🏗️ 技术架构

### 架构图

```
┌─────────────────────────────────────┐
│     SmartHub Android 应用           │
│    (运行在二手平板上)               │
├─────────────────────────────────────┤
│  UI层 - Jetpack Compose             │
├─────────────────────────────────────┤
│  业务层 - ViewModel + UseCase       │
├─────────────────────────────────────┤
│  数据层                             │
│  ├─ Room数据库 (设备/场景/配置)    │
│  ├─ DataStore (用户偏好)            │
│  └─ 内存缓存                        │
├─────────────────────────────────────┤
│  云平台插件层                       │
│  ├─ 腾讯连连插件 ──→ 腾讯云API     │
│  ├─ 天猫精灵插件 ──→ 阿里云API     │
│  ├─ 小米IoT插件  ──→ 小米云API     │
│  ├─ 华为HiLink插件 ──→ 华为云API   │
│  └─ 更多平台...                     │
└─────────────────────────────────────┘
         ↓           ↓           ↓
    [WiFi设备]  [BLE设备]  [云端设备]
```

### 技术栈

**核心框架**
- 语言：Kotlin
- UI：Jetpack Compose + Material 3
- 架构：MVVM + Clean Architecture
- 异步：Coroutines + Flow

**数据存储**
- 数据库：Room + SQLite
- 配置：DataStore
- 缓存：内存缓存 + 持久化

**网络通信**
- HTTP：OkHttp + Retrofit
- MQTT：Eclipse Paho
- WebSocket：OkHttp

**云平台集成**
- 各平台官方 SDK
- 统一插件接口
- 热插拔架构

### 项目结构

```
SmartHub/
├── app/                          # 主应用模块
│   ├── core/                     # 核心框架
│   │   ├── network/             # 网络层
│   │   ├── database/            # 数据库
│   │   └── utils/               # 工具类
│   ├── feature/                  # 功能模块
│   │   ├── device/              # 设备管理
│   │   ├── scene/               # 场景联动
│   │   ├── dashboard/           # 仪表板
│   │   └── settings/            # 设置
│   └── platform/                 # 云平台插件
│       ├── tencent/             # 腾讯连连
│       ├── tmall/               # 天猫精灵
│       ├── xiaomi/              # 小米IoT
│       └── huawei/              # 华为HiLink
├── docs/                         # 文档
│   ├── 商业计划书.md
│   ├── 技术开发计划.md
│   └── api/                     # API文档
├── scripts/                      # 脚本工具
└── tests/                        # 测试
```

---

## 🎯 支持的云平台

| 平台 | 状态 | 版本 | 说明 |
|------|------|------|------|
| 腾讯连连 | 📅 计划中 | - | MVP 阶段优先 |
| 天猫精灵 | 📅 计划中 | - | MVP 阶段优先 |
| 小米IoT | 📅 计划中 | - | MVP 阶段 |
| 华为HiLink | 📅 计划中 | - | Phase 2 |
| 百度智能云 | 📅 计划中 | - | Phase 2 |
| 京东小京鱼 | 📅 计划中 | - | Phase 2 |
| 涂鸦智能 | 📅 计划中 | - | Phase 2 |
| AWS IoT | 📅 计划中 | - | Phase 3 |
| Google Home | 📅 计划中 | - | Phase 3 |

> 💡 **开发者贡献**：欢迎提交新平台插件！查看 [插件开发指南](docs/plugin-development.md)

---

## 🛠️ 开发指南

### 环境准备

**必需工具**
- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 17
- Android SDK (API 26+)
- Git

**推荐工具**
- Android Emulator 或真实设备
- Gradle 8.0+

### 构建项目

```bash
# 1. 克隆项目
git clone https://github.com/ardondon/SmartHub.git
cd SmartHub

# 2. 安装依赖
./gradlew build

# 3. 运行测试
./gradlew test

# 4. 构建 Debug 版本
./gradlew assembleDebug

# 5. 构建 Release 版本
./gradlew assembleRelease
```

### 代码规范

我们遵循 [Kotlin 官方代码规范](https://kotlinlang.org/docs/coding-conventions.html)：

```kotlin
// ✅ 推荐
class DeviceManager(
    private val context: Context,
    private val repository: DeviceRepository
) {
    suspend fun connectDevice(device: Device): Result<Unit> {
        return try {
            repository.connect(device)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// ❌ 不推荐
class devicemanager(ctx: Context) {
    fun connect(d: Device) {
        // 没有错误处理
        repository.connect(d)
    }
}
```

### 提交规范

使用 [Conventional Commits](https://www.conventionalcommits.org/)：

```bash
feat(device): 添加设备批量控制功能
fix(mqtt): 修复断线重连问题
docs(api): 更新 API 文档
refactor(scene): 重构场景引擎
test(platform): 添加腾讯连连单元测试
```

---

## 🤝 贡献指南

我们欢迎所有形式的贡献！

### 如何贡献

1. **Fork** 本仓库
2. **创建分支** (`git checkout -b feature/AmazingFeature`)
3. **提交更改** (`git commit -m 'feat: Add some AmazingFeature'`)
4. **推送分支** (`git push origin feature/AmazingFeature`)
5. **提交 Pull Request**

### 贡献类型

- 🐛 **Bug 修复** - 发现并修复 Bug
- ✨ **新功能** - 实现新的功能特性
- 📝 **文档** - 改进文档和示例
- 🔌 **插件** - 开发新的云平台插件
- 🎨 **UI/UX** - 改进用户界面和体验
- ⚡ **性能优化** - 提升应用性能
- 🧪 **测试** - 增加测试覆盖率

### 行为准则

请阅读我们的 [行为准则](docs/CODE_OF_CONDUCT.md)，共同维护友好的社区环境。

---

## 📅 开发路线图

### Phase 1: MVP 开发 (📅 规划中 - 2025 Q1-Q2)
- 📋 Android 基础框架搭建
- 📋 腾讯连连、天猫精灵集成
- 📋 设备管理、场景联动
- 📋 基础 UI 设计与实现

### Phase 2: 公测与优化 (📅 规划中 - 2025 Q3-Q4)
- 📋 接入更多云平台（华为、百度、京东）
- 📋 AI 场景推荐
- 📋 多网关协同
- 📋 插件商店

### Phase 3: 规模化 (📅 规划中 - 2026)
- 📋 企业版功能
- 📋 性能优化（支持 100+ 设备）
- 📋 国际化（英文、日文等）
- 📋 开发者生态建设

查看完整路线图：[技术开发计划](docs/技术开发计划.md)

---

## 📊 项目状态

![GitHub commit activity](https://img.shields.io/github/commit-activity/m/ardondon/SmartHub)
![GitHub issues](https://img.shields.io/github/issues/ardondon/SmartHub)
![GitHub pull requests](https://img.shields.io/github/issues-pr/ardondon/SmartHub)

**项目状态**：📋 规划阶段  
**当前版本**：v0.0.0 (尚未发布)  
**开发进度**：文档编写完成，代码开发待启动  
**预计首个版本**：2025 Q2

---

## 📱 应用截图

<div align="center">

| 设备列表 | 场景联动 | 数据看板 |
|---------|---------|---------|
| ![设备列表](docs/images/screenshot-devices.png) | ![场景](docs/images/screenshot-scenes.png) | ![看板](docs/images/screenshot-dashboard.png) |

</div>

---

## 💬 社区与支持

### 获取帮助

- 📖 [使用文档](docs/user-guide.md)
- 💡 [常见问题](docs/FAQ.md)
- 🐛 [问题反馈](https://github.com/ardondon/SmartHub/issues)
- 💬 [讨论区](https://github.com/ardondon/SmartHub/discussions)

### 社交媒体

- 🎬 [Bilibili](https://space.bilibili.com/xxx) - 视频教程
- 📝 [知乎](https://www.zhihu.com/people/xxx) - 技术分享
- 📷 [小红书](https://www.xiaohongshu.com/user/xxx) - 使用技巧
- 💬 QQ 群：123456789
- 💬 微信群：扫码加入

---

## 📄 许可证

本项目采用 [Apache License 2.0](docs/LICENSE) 开源协议。

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

## 🙏 致谢

感谢以下开源项目和社区：

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - 现代 Android UI 工具包
- [Kotlin](https://kotlinlang.org/) - 优雅的编程语言
- [Eclipse Paho](https://www.eclipse.org/paho/) - MQTT 客户端
- [OkHttp](https://square.github.io/okhttp/) - HTTP 客户端
- [Room](https://developer.android.com/training/data-storage/room) - 数据库
- 所有贡献者和支持者 ❤️

---

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=ardondon/SmartHub&type=Date)](https://star-history.com/#ardondon/SmartHub&Date)

---

<div align="center">

**如果这个项目对你有帮助，请给我们一个 ⭐ Star！**

Made with ardondon ❤️ by SmartHub Team

[官网](https://smarthub.io) · [文档](https://docs.smarthub.io) · [博客](https://blog.smarthub.io)

</div>
