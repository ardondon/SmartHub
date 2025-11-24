package com.smarthub.gateway.data.repository

import com.smarthub.gateway.data.local.dao.DeviceDao
import com.smarthub.gateway.data.local.entity.DeviceEntity
import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.model.DeviceStatus
import com.smarthub.gateway.domain.model.Platform
import com.smarthub.gateway.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 设备仓库实现
 * 
 * 负责设备数据的本地存储和云端同步
 */
@Singleton
class DeviceRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao
) : DeviceRepository {
    
    override fun getAllDevices(): Flow<List<Device>> {
        return deviceDao.getAllDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getDeviceById(deviceId: String): Device? {
        return deviceDao.getDeviceById(deviceId)?.toDomain()
    }
    
    override fun getDevicesByPlatform(platform: Platform): Flow<List<Device>> {
        return deviceDao.getDevicesByPlatform(platform.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getOnlineDevices(): Flow<List<Device>> {
        return deviceDao.getOnlineDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getOfflineDevices(): Flow<List<Device>> {
        return deviceDao.getOfflineDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun saveDevice(device: Device) {
        try {
            val entity = DeviceEntity.fromDomain(device)
            deviceDao.insertDevice(entity)
            Timber.d("Device saved: ${device.name}")
        } catch (e: Exception) {
            Timber.e(e, "Failed to save device: ${device.name}")
            throw e
        }
    }
    
    override suspend fun saveDevices(devices: List<Device>) {
        try {
            val entities = devices.map { DeviceEntity.fromDomain(it) }
            deviceDao.insertDevices(entities)
            Timber.d("Saved ${devices.size} devices")
        } catch (e: Exception) {
            Timber.e(e, "Failed to save devices")
            throw e
        }
    }
    
    override suspend fun deleteDevice(deviceId: String) {
        try {
            deviceDao.deleteDeviceById(deviceId)
            Timber.d("Device deleted: $deviceId")
        } catch (e: Exception) {
            Timber.e(e, "Failed to delete device: $deviceId")
            throw e
        }
    }
    
    override suspend fun deleteAllDevices() {
        try {
            deviceDao.deleteAllDevices()
            Timber.d("All devices deleted")
        } catch (e: Exception) {
            Timber.e(e, "Failed to delete all devices")
            throw e
        }
    }
    
    override suspend fun updateDeviceOnlineStatus(deviceId: String, isOnline: Boolean) {
        try {
            deviceDao.updateDeviceOnlineStatus(deviceId, isOnline)
            Timber.d("Device $deviceId online status updated: $isOnline")
        } catch (e: Exception) {
            Timber.e(e, "Failed to update device online status")
            throw e
        }
    }
    
    override suspend fun getDeviceCount(): Int {
        return deviceDao.getDeviceCount()
    }
    
    override suspend fun syncDevicesFromCloud(platform: Platform): Result<Unit> {
        // TODO: 实现云平台设备同步
        // 这将在集成腾讯云SDK后实现
        Timber.w("Cloud sync not implemented yet for platform: $platform")
        return Result.failure(NotImplementedError("Cloud sync will be implemented in Week 2"))
    }
}
