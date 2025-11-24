package com.smarthub.gateway.domain.usecase

import com.smarthub.gateway.domain.repository.DeviceRepository
import javax.inject.Inject

/**
 * 删除设备用例
 */
class DeleteDeviceUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    suspend operator fun invoke(deviceId: String) {
        deviceRepository.deleteDevice(deviceId)
    }
}
