package com.smarthub.gateway.presentation.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.model.DeviceStatus
import com.smarthub.gateway.domain.model.DeviceType
import com.smarthub.gateway.domain.model.Platform
import com.smarthub.gateway.domain.usecase.GetDevicesUseCase
import com.smarthub.gateway.domain.usecase.SaveDeviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * 设备列表 UI 状态
 */
data class DevicesUiState(
    val devices: List<Device> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * 设备列表 ViewModel
 */
@HiltViewModel
class DevicesViewModel @Inject constructor(
    private val getDevicesUseCase: GetDevicesUseCase,
    private val saveDeviceUseCase: SaveDeviceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    init {
        loadDevices()
    }

    private fun loadDevices() {
        viewModelScope.launch {
            getDevicesUseCase()
                .onStart { 
                    _uiState.value = _uiState.value.copy(isLoading = true) 
                }
                .catch { e -> 
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
                .collect { devices ->
                    _uiState.value = _uiState.value.copy(
                        devices = devices,
                        isLoading = false,
                        error = null
                    )
                    
                    // 如果列表为空，添加一些测试数据（仅MVP阶段使用）
                    if (devices.isEmpty()) {
                        addTestData()
                    }
                }
        }
    }

    /**
     * 添加测试数据
     */
    fun addTestData() {
        viewModelScope.launch {
            val testDevices = listOf(
                createTestDevice("客厅灯", DeviceType.LIGHT, true),
                createTestDevice("卧室空调", DeviceType.AIR_CONDITIONER, false),
                createTestDevice("大门锁", DeviceType.LOCK, true),
                createTestDevice("温湿度传感器", DeviceType.SENSOR, true)
            )
            
            testDevices.forEach { device ->
                saveDeviceUseCase(device)
            }
        }
    }

    private fun createTestDevice(name: String, type: DeviceType, isOnline: Boolean): Device {
        return Device(
            id = UUID.randomUUID().toString(),
            name = name,
            type = type,
            platform = Platform.TENCENT,
            status = if (isOnline) DeviceStatus.NORMAL else DeviceStatus.OFFLINE,
            isOnline = isOnline,
            properties = emptyMap(),
            lastUpdateTime = System.currentTimeMillis()
        )
    }
}
