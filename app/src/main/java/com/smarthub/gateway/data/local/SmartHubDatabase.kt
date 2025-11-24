package com.smarthub.gateway.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smarthub.gateway.data.local.dao.DeviceDao
import com.smarthub.gateway.data.local.entity.Converters
import com.smarthub.gateway.data.local.entity.DeviceEntity

/**
 * SmartHub Room数据库
 * 
 * 当前版本: 1
 * 实体表: devices
 */
@Database(
    entities = [DeviceEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SmartHubDatabase : RoomDatabase() {
    
    /**
     * 获取设备DAO
     */
    abstract fun deviceDao(): DeviceDao
    
    companion object {
        const val DATABASE_NAME = "smarthub_database"
    }
}
