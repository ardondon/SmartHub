package com.smarthub.gateway.domain.repository

import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.model.Platform
import kotlinx.coroutines.flow.Flow

/**
 * 设备仓库接口
 * 
 * 定义数据访问的抽象接口，具体实现在data层
 */
interface DeviceRepository {
    
    /**
     * 获取所有设备（Flow自动更新）
     */
    fun getAllDevices(): Flow<List<Device>>
    
    /**
     * 根据ID获取设备
     */
    suspend fun getDeviceById(deviceId: String): Device?
    
    /**
     * 根据平台获取设备
     */
    fun getDevicesByPlatform(platform: Platform): Flow<List<Device>>
    
    /**
     * 获取在线设备
     */
    fun getOnlineDevices(): Flow<List<Device>>
    
    /**
     * 获取离线设备
     */
    fun getOfflineDevices(): Flow<List<Device>>
    
    /**
     * 保存设备
     */
    suspend fun saveDevice(device: Device)
    
    /**
     * 批量保存设备
     */
    suspend fun saveDevices(devices: List<Device>)
    
    /**
     * 删除设备
     */
    suspend fun deleteDevice(deviceId: String)
    
    /**
     * 删除所有设备
     */
    suspend fun deleteAllDevices()
    
    /**
     * 更新设备在线状态
     */
    suspend fun updateDeviceOnlineStatus(deviceId: String, isOnline: Boolean)
    
    /**
     * 获取设备总数
     */
    suspend fun getDeviceCount(): Int
    
    /**
     * 同步设备（从云平台）
     */
    suspend fun syncDevicesFromCloud(platform: Platform): Result<Unit>
}
