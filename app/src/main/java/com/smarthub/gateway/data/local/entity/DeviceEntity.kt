package com.smarthub.gateway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.model.DeviceStatus
import com.smarthub.gateway.domain.model.DeviceType
import com.smarthub.gateway.domain.model.Platform

/**
 * Room数据库设备实体
 * 
 * 用于本地数据库存储
 */
@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,               // 存储为字符串
    val platform: String,           // 存储为字符串
    val status: String,             // 存储为字符串
    val isOnline: Boolean,
    val properties: String,         // JSON字符串
    val lastUpdateTime: Long
) {
    /**
     * 转换为领域模型
     */
    fun toDomain(): Device {
        return Device(
            id = id,
            name = name,
            type = DeviceType.valueOf(type),
            platform = Platform.valueOf(platform),
            status = DeviceStatus.valueOf(status),
            isOnline = isOnline,
            properties = Converters.fromJsonToMap(properties),
            lastUpdateTime = lastUpdateTime
        )
    }

    companion object {
        /**
         * 从领域模型创建实体
         */
        fun fromDomain(device: Device): DeviceEntity {
            return DeviceEntity(
                id = device.id,
                name = device.name,
                type = device.type.name,
                platform = device.platform.name,
                status = device.status.name,
                isOnline = device.isOnline,
                properties = Converters.fromMapToJson(device.properties),
                lastUpdateTime = device.lastUpdateTime
            )
        }
    }
}

/**
 * Room类型转换器
 * 
 * 用于将复杂类型存储到数据库
 */
object Converters {
    private val gson = Gson()

    /**
     * Map转JSON字符串
     */
    @TypeConverter
    fun fromMapToJson(value: Map<String, Any>): String {
        return gson.toJson(value)
    }

    /**
     * JSON字符串转Map
     */
    @TypeConverter
    fun fromJsonToMap(value: String): Map<String, Any> {
        val type = object : TypeToken<Map<String, Any>>() {}.type
        return gson.fromJson(value, type) ?: emptyMap()
    }
}
