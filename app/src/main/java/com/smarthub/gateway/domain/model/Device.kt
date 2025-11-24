package com.smarthub.gateway.domain.model

/**
 * 设备领域模型
 * 
 * 用于业务层的设备表示，不依赖于任何框架
 */
data class Device(
    val id: String,                      // 设备唯一ID
    val name: String,                    // 设备名称
    val type: DeviceType,                // 设备类型
    val platform: Platform,              // 所属平台
    val status: DeviceStatus,            // 设备状态
    val isOnline: Boolean,               // 是否在线
    val properties: Map<String, Any>,    // 设备属性（如温度、湿度等）
    val lastUpdateTime: Long             // 最后更新时间（时间戳）
)

/**
 * 设备类型枚举
 */
enum class DeviceType {
    LIGHT,          // 灯具
    SWITCH,         // 开关
    SENSOR,         // 传感器
    CAMERA,         // 摄像头
    LOCK,           // 门锁
    CURTAIN,        // 窗帘
    AIR_CONDITIONER,// 空调
    TV,             // 电视
    SPEAKER,        // 音箱
    UNKNOWN         // 未知类型
}

/**
 * 云平台枚举
 */
enum class Platform {
    TENCENT,        // 腾讯连连
    TMALL,          // 天猫精灵
    XIAOMI,         // 小米IoT
    HUAWEI,         // 华为HiLink
    UNKNOWN         // 未知平台
}

/**
 * 设备状态枚举
 */
enum class DeviceStatus {
    NORMAL,         // 正常
    OFFLINE,        // 离线
    ERROR,          // 错误
    UPDATING        // 更新中
}
