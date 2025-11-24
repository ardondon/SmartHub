package com.smarthub.gateway

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * SmartHub应用程序入口
 * 
 * 职责:
 * - 初始化Hilt依赖注入
 * - 配置Timber日志
 * - 全局异常处理
 * - 应用生命周期管理
 */
@HiltAndroidApp
class SmartHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // 初始化Timber日志
        initTimber()
        
        Timber.i("SmartHub Application started")
        Timber.d("Version: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
    }

    /**
     * 初始化Timber日志系统
     * Debug版本输出详细日志，Release版本只输出警告和错误
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            // Debug模式: 输出所有日志到Logcat
            Timber.plant(Timber.DebugTree())
        } else {
            // Release模式: 只记录警告和错误，可以考虑发送到崩溃报告系统
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (priority >= android.util.Log.WARN) {
                        // TODO: 发送到崩溃报告系统（如Firebase Crashlytics）
                        android.util.Log.println(priority, tag ?: "SmartHub", message)
                        t?.printStackTrace()
                    }
                }
            })
        }
    }

    override fun onTerminate() {
        Timber.i("SmartHub Application terminated")
        super.onTerminate()
    }
}
