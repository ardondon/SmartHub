package com.smarthub.gateway.domain.usecase

import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.repository.DeviceRepository
import javax.inject.Inject

/**
 * 保存设备用例
 */
class SaveDeviceUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    suspend operator fun invoke(device: Device) {
        deviceRepository.saveDevice(device)
    }
}
