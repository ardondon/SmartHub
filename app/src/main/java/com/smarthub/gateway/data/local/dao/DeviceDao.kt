package com.smarthub.gateway.data.local.dao

import androidx.room.*
import com.smarthub.gateway.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

/**
 * 设备数据访问对象
 * 
 * 定义所有设备相关的数据库操作
 */
@Dao
interface DeviceDao {
    
    /**
     * 查询所有设备（Flow自动更新）
     */
    @Query("SELECT * FROM devices ORDER BY lastUpdateTime DESC")
    fun getAllDevices(): Flow<List<DeviceEntity>>
    
    /**
     * 根据ID查询设备
     */
    @Query("SELECT * FROM devices WHERE id = :deviceId")
    suspend fun getDeviceById(deviceId: String): DeviceEntity?
    
    /**
     * 根据平台查询设备
     */
    @Query("SELECT * FROM devices WHERE platform = :platform")
    fun getDevicesByPlatform(platform: String): Flow<List<DeviceEntity>>
    
    /**
     * 查询在线设备
     */
    @Query("SELECT * FROM devices WHERE isOnline = 1")
    fun getOnlineDevices(): Flow<List<DeviceEntity>>
    
    /**
     * 查询离线设备
     */
    @Query("SELECT * FROM devices WHERE isOnline = 0")
    fun getOfflineDevices(): Flow<List<DeviceEntity>>
    
    /**
     * 插入设备（如果存在则替换）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: DeviceEntity)
    
    /**
     * 批量插入设备
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: List<DeviceEntity>)
    
    /**
     * 更新设备
     */
    @Update
    suspend fun updateDevice(device: DeviceEntity)
    
    /**
     * 删除设备
     */
    @Delete
    suspend fun deleteDevice(device: DeviceEntity)
    
    /**
     * 根据ID删除设备
     */
    @Query("DELETE FROM devices WHERE id = :deviceId")
    suspend fun deleteDeviceById(deviceId: String)
    
    /**
     * 删除所有设备
     */
    @Query("DELETE FROM devices")
    suspend fun deleteAllDevices()
    
    /**
     * 更新设备在线状态
     */
    @Query("UPDATE devices SET isOnline = :isOnline WHERE id = :deviceId")
    suspend fun updateDeviceOnlineStatus(deviceId: String, isOnline: Boolean)
    
    /**
     * 更新设备状态
     */
    @Query("UPDATE devices SET status = :status WHERE id = :deviceId")
    suspend fun updateDeviceStatus(deviceId: String, status: String)
    
    /**
     * 获取设备总数
     */
    @Query("SELECT COUNT(*) FROM devices")
    suspend fun getDeviceCount(): Int
}
