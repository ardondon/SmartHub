# 贡献指南 | Contributing Guide

感谢你考虑为 SmartHub 项目做出贡献！

Thank you for considering contributing to SmartHub!

[中文](#中文) | [English](#english)

---

## 中文

### 🎯 贡献方式

我们欢迎各种形式的贡献：

#### 1. 报告 Bug
- 在 [Issues](https://github.com/ardondon/SmartHub/issues) 中搜索，确认问题未被报告
- 使用 Bug 报告模板
- 提供详细的复现步骤
- 包含设备信息和日志

#### 2. 提出新功能
- 先在 [Discussions](https://github.com/ardondon/SmartHub/discussions) 讨论
- 说明功能的使用场景和价值
- 如果被认可，创建 Feature Request Issue

#### 3. 提交代码
- Fork 项目并创建分支
- 遵循代码规范
- 编写测试用例
- 提交 Pull Request

#### 4. 改进文档
- 修正拼写错误
- 补充缺失内容
- 翻译文档
- 添加示例

#### 5. 开发插件
- 查看 [插件开发指南](docs/plugin-development.md)
- 为新的云平台开发适配插件
- 分享到插件商店

---

### 🔧 开发环境配置

#### 必需工具

```bash
# 1. 安装 JDK 17
# Windows: https://adoptium.net/
# macOS: brew install openjdk@17
# Linux: apt install openjdk-17-jdk

# 2. 安装 Android Studio
# 下载地址: https://developer.android.com/studio

# 3. 配置 Android SDK
# 打开 Android Studio -> Settings -> Android SDK
# 安装 API 26 (Android 8.0) 及以上版本
```

#### 克隆项目

```bash
# Fork 项目后克隆你的 Fork
git clone https://github.com/YOUR_USERNAME/SmartHub.git
cd SmartHub

# 添加上游仓库
git remote add upstream https://github.com/ardondon/SmartHub.git

# 获取最新代码
git fetch upstream
git merge upstream/main
```

#### 构建项目

```bash
# 构建 Debug 版本
./gradlew assembleDebug

# 运行单元测试
./gradlew test

# 运行 UI 测试
./gradlew connectedAndroidTest

# 代码检查
./gradlew lint
```

---

### 📝 代码规范

#### Kotlin 风格

遵循 [Kotlin 官方编码规范](https://kotlinlang.org/docs/coding-conventions.html)：

```kotlin
// ✅ 推荐
class DeviceManager @Inject constructor(
    private val context: Context,
    private val repository: DeviceRepository
) {
    suspend fun connectDevice(device: Device): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                repository.connect(device)
                Result.success(Unit)
            } catch (e: Exception) {
                Logger.e(TAG, "Failed to connect device", e)
                Result.failure(e)
            }
        }
    }
    
    companion object {
        private const val TAG = "DeviceManager"
    }
}

// ❌ 不推荐
class devicemanager(ctx: Context) {
    fun connect(d: Device) {
        repository.connect(d)  // 缺少错误处理
    }
}
```

#### 命名规范

```kotlin
// 类名：PascalCase
class DeviceManager
class SmartHomeService

// 函数名：camelCase
fun connectDevice()
fun getDeviceList()

// 常量：UPPER_SNAKE_CASE
const val MAX_RETRY_COUNT = 3
const val DEFAULT_TIMEOUT = 5000L

// 变量：camelCase
val deviceList = listOf<Device>()
var isConnected = false

// 私有属性：_camelCase（可选）
private val _devices = MutableStateFlow<List<Device>>(emptyList())
val devices = _devices.asStateFlow()
```

#### Compose UI 规范

```kotlin
// ✅ 推荐
@Composable
fun DeviceCard(
    device: Device,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = device.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = device.status,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

// 预览
@Preview(showBackground = true)
@Composable
private fun DeviceCardPreview() {
    SmartHubTheme {
        DeviceCard(
            device = Device(name = "客厅灯", status = "在线"),
            onClick = {}
        )
    }
}
```

#### 注释规范

```kotlin
/**
 * 设备管理器，负责设备的连接、控制和状态同步
 *
 * @property context Android 上下文
 * @property repository 设备数据仓库
 */
class DeviceManager @Inject constructor(
    private val context: Context,
    private val repository: DeviceRepository
) {
    /**
     * 连接指定设备
     *
     * @param device 要连接的设备
     * @return Result<Unit> 成功返回 Success，失败返回 Failure
     */
    suspend fun connectDevice(device: Device): Result<Unit> {
        // 实现代码...
    }
}
```

---

### 🧪 测试规范

#### 单元测试

```kotlin
@Test
fun `connectDevice should return success when connection succeeds`() = runTest {
    // Given
    val device = Device(id = "1", name = "Test Device")
    coEvery { repository.connect(device) } returns Unit
    
    // When
    val result = deviceManager.connectDevice(device)
    
    // Then
    assertTrue(result.isSuccess)
    coVerify { repository.connect(device) }
}

@Test
fun `connectDevice should return failure when connection fails`() = runTest {
    // Given
    val device = Device(id = "1", name = "Test Device")
    val exception = IOException("Network error")
    coEvery { repository.connect(device) } throws exception
    
    // When
    val result = deviceManager.connectDevice(device)
    
    // Then
    assertTrue(result.isFailure)
    assertEquals(exception, result.exceptionOrNull())
}
```

#### UI 测试

```kotlin
@Test
fun deviceCard_displaysDeviceInfo() {
    composeTestRule.setContent {
        DeviceCard(
            device = Device(name = "客厅灯", status = "在线"),
            onClick = {}
        )
    }
    
    composeTestRule
        .onNodeWithText("客厅灯")
        .assertIsDisplayed()
    
    composeTestRule
        .onNodeWithText("在线")
        .assertIsDisplayed()
}
```

#### 测试覆盖率要求

- 核心业务逻辑：≥90%
- UI 层：≥60%
- 整体项目：≥80%

---

### 📋 提交规范

#### Commit Message 格式

使用 [Conventional Commits](https://www.conventionalcommits.org/) 规范：

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Type 类型：**

- `feat`: 新功能
- `fix`: Bug 修复
- `docs`: 文档更新
- `style`: 代码格式（不影响代码运行）
- `refactor`: 重构
- `perf`: 性能优化
- `test`: 测试相关
- `build`: 构建系统或依赖更新
- `ci`: CI 配置更新
- `chore`: 其他杂项

**Scope 范围：**

- `device`: 设备管理
- `scene`: 场景联动
- `platform`: 云平台插件
- `ui`: 用户界面
- `core`: 核心框架
- `mqtt`: MQTT 通信
- `database`: 数据库

**示例：**

```bash
feat(device): 添加设备批量控制功能

- 支持同时控制多个设备
- 添加批量操作确认对话框
- 优化批量操作性能

Closes #123
```

```bash
fix(mqtt): 修复断线重连失败的问题

在网络不稳定时，MQTT 客户端可能无法自动重连。
本次修复增加了重连重试机制和指数退避策略。

Fixes #456
```

---

### 🔄 Pull Request 流程

#### 1. 创建分支

```bash
# 从 main 分支创建功能分支
git checkout main
git pull upstream main
git checkout -b feature/your-feature-name

# 或 Bug 修复分支
git checkout -b fix/bug-description
```

#### 2. 开发和提交

```bash
# 添加修改
git add .

# 提交（遵循 Commit 规范）
git commit -m "feat(device): add batch control feature"

# 推送到你的 Fork
git push origin feature/your-feature-name
```

#### 3. 创建 Pull Request

1. 访问你的 Fork 页面
2. 点击 "Compare & pull request"
3. 填写 PR 模板：
   - 描述修改内容
   - 关联相关 Issue
   - 添加测试说明
   - 上传截图（如果有 UI 变更）
4. 提交 PR

#### 4. Code Review

- 维护者会审查你的代码
- 可能会要求修改
- 及时响应评论
- 修改后更新 PR

#### 5. 合并

- 所有检查通过
- 获得至少 1 个 Approve
- 维护者会合并你的 PR

---

### 🎨 UI/UX 设计规范

#### Material 3 Design

- 使用 Material 3 组件
- 遵循 Material Design 指南
- 支持深色模式
- 注重无障碍访问

#### 颜色主题

```kotlin
// 浅色主题
val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),
    secondary = Color(0xFF625B71),
    tertiary = Color(0xFF7D5260)
)

// 深色主题
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    secondary = Color(0xFFCCC2DC),
    tertiary = Color(0xFFEFB8C8)
)
```

#### 间距规范

```kotlin
object Spacing {
    val extraSmall = 4.dp
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
    val extraLarge = 32.dp
}
```

---

### 🌍 国际化

#### 添加翻译

```xml
<!-- values/strings.xml (默认英文) -->
<string name="app_name">SmartHub</string>
<string name="devices">Devices</string>

<!-- values-zh-rCN/strings.xml (简体中文) -->
<string name="app_name">SmartHub</string>
<string name="devices">设备</string>

<!-- values-ja/strings.xml (日文) -->
<string name="app_name">SmartHub</string>
<string name="devices">デバイス</string>
```

---

### 🔌 插件开发

#### 创建新平台插件

1. 实现 `CloudPlatformPlugin` 接口
2. 注册插件到 `PluginManager`
3. 编写单元测试
4. 更新文档

示例：

```kotlin
class CustomPlatformPlugin : CloudPlatformPlugin {
    override val platformId = "custom_platform"
    override val platformName = "Custom Platform"
    
    override suspend fun connect(config: PlatformConfig): Result<Unit> {
        // 实现连接逻辑
    }
    
    override suspend fun disconnect() {
        // 实现断开逻辑
    }
    
    // 其他接口实现...
}
```

详见：[插件开发指南](docs/plugin-development.md)

---

### ❓ 常见问题

#### Q: 如何运行项目？

```bash
# 使用 Android Studio 打开项目
# 连接 Android 设备或启动模拟器
# 点击 Run 按钮或使用快捷键 Shift+F10
```

#### Q: 构建失败怎么办？

```bash
# 清理项目
./gradlew clean

# 删除 .gradle 缓存
rm -rf .gradle

# 重新构建
./gradlew build
```

#### Q: 如何调试 MQTT 连接？

启用详细日志：

```kotlin
Logger.setLogLevel(LogLevel.VERBOSE)
```

#### Q: 如何联系维护者？

- GitHub Issues：技术问题
- GitHub Discussions：讨论
- Email：紧急问题

---

### 📜 行为准则

请阅读并遵守我们的 [行为准则](CODE_OF_CONDUCT.md)。

---

### 🙏 感谢

感谢所有为 SmartHub 做出贡献的开发者！

[![Contributors](https://contrib.rocks/image?repo=ardondon/SmartHub)](https://github.com/ardondon/SmartHub/graphs/contributors)

---

## English

### 🎯 Ways to Contribute

We welcome all forms of contributions:

#### 1. Report Bugs
- Search in [Issues](https://github.com/ardondon/SmartHub/issues) first
- Use Bug Report template
- Provide detailed reproduction steps
- Include device info and logs

#### 2. Suggest Features
- Discuss in [Discussions](https://github.com/ardondon/SmartHub/discussions) first
- Explain use cases and value
- Create Feature Request Issue if approved

#### 3. Submit Code
- Fork and create branch
- Follow code style
- Write tests
- Submit Pull Request

#### 4. Improve Documentation
- Fix typos
- Add missing content
- Translate docs
- Add examples

#### 5. Develop Plugins
- Check [Plugin Development Guide](docs/plugin-development.md)
- Develop adapters for new platforms
- Share in plugin marketplace

---

### 🔧 Development Setup

#### Prerequisites

```bash
# 1. Install JDK 17
# Windows: https://adoptium.net/
# macOS: brew install openjdk@17
# Linux: apt install openjdk-17-jdk

# 2. Install Android Studio
# Download: https://developer.android.com/studio

# 3. Configure Android SDK
# Android Studio -> Settings -> Android SDK
# Install API 26 (Android 8.0) and above
```

#### Clone Project

```bash
# Clone your fork
git clone https://github.com/YOUR_USERNAME/SmartHub.git
cd SmartHub

# Add upstream
git remote add upstream https://github.com/ardondon/SmartHub.git

# Fetch latest
git fetch upstream
git merge upstream/main
```

#### Build

```bash
# Build debug
./gradlew assembleDebug

# Run tests
./gradlew test

# Run UI tests
./gradlew connectedAndroidTest

# Lint
./gradlew lint
```

---

### 📝 Code Style

Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html).

---

### 📋 Commit Convention

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
feat(device): add batch control feature
fix(mqtt): fix reconnection issue
docs(api): update API documentation
```

---

### 🔄 Pull Request Process

1. Create branch from main
2. Make changes and commit
3. Push to your fork
4. Create Pull Request
5. Wait for review
6. Address feedback
7. Get merged

---

### ❓ FAQ

See Chinese version above for detailed FAQ.

---

### 📜 Code of Conduct

Please read our [Code of Conduct](CODE_OF_CONDUCT.md).

---

### 🙏 Thanks

Thanks to all contributors!

[![Contributors](https://contrib.rocks/image?repo=ardondon/SmartHub)](https://github.com/ardondon/SmartHub/graphs/contributors)
