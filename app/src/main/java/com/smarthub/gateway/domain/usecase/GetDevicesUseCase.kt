package com.smarthub.gateway.domain.usecase

import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 获取所有设备用例
 */
class GetDevicesUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    operator fun invoke(): Flow<List<Device>> {
        return deviceRepository.getAllDevices()
    }
}
