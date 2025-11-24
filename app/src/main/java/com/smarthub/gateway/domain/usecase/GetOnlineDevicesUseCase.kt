package com.smarthub.gateway.domain.usecase

import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 获取在线设备用例
 */
class GetOnlineDevicesUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    operator fun invoke(): Flow<List<Device>> {
        return deviceRepository.getOnlineDevices()
    }
}
