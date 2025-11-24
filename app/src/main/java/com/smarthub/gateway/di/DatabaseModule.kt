package com.smarthub.gateway.di

import android.content.Context
import androidx.room.Room
import com.smarthub.gateway.data.local.SmartHubDatabase
import com.smarthub.gateway.data.local.dao.DeviceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 数据库依赖注入模块
 * 
 * 提供Room数据库和DAO实例
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    /**
     * 提供SmartHub数据库实例
     */
    @Provides
    @Singleton
    fun provideSmartHubDatabase(
        @ApplicationContext context: Context
    ): SmartHubDatabase {
        return Room.databaseBuilder(
            context,
            SmartHubDatabase::class.java,
            SmartHubDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // MVP阶段，数据库升级时直接清空
            .build()
    }
    
    /**
     * 提供DeviceDao实例
     */
    @Provides
    @Singleton
    fun provideDeviceDao(database: SmartHubDatabase): DeviceDao {
        return database.deviceDao()
    }
}
