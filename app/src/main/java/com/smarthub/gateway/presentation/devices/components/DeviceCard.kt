package com.smarthub.gateway.presentation.devices.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.smarthub.gateway.domain.model.Device
import com.smarthub.gateway.domain.model.DeviceType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceCard(
    device: Device,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (device.isOnline) 
                MaterialTheme.colorScheme.surfaceVariant 
            else 
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 设备图标
            Surface(
                shape = CircleShape,
                color = if (device.isOnline) 
                    MaterialTheme.colorScheme.primaryContainer 
                else 
                    MaterialTheme.colorScheme.surface,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = getDeviceIcon(device.type),
                        contentDescription = null,
                        tint = if (device.isOnline) 
                            MaterialTheme.colorScheme.onPrimaryContainer 
                        else 
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 设备信息
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = device.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (device.isOnline) 
                        MaterialTheme.colorScheme.onSurfaceVariant 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // 在线状态指示点
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = if (device.isOnline) Color.Green else Color.Gray,
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (device.isOnline) "在线" else "离线",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "•  ${device.platform.name}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )
                }
            }
            
            // 开关（如果是开关类设备）
            if (device.type == DeviceType.LIGHT || device.type == DeviceType.SWITCH) {
                Switch(
                    checked = device.isOnline, // 暂时用在线状态模拟开关
                    onCheckedChange = { /* TODO: 实现控制 */ },
                    enabled = device.isOnline
                )
            }
        }
    }
}

private fun getDeviceIcon(type: DeviceType): ImageVector {
    return when (type) {
        DeviceType.LIGHT -> Icons.Filled.Lightbulb
        DeviceType.SWITCH -> Icons.Filled.ToggleOn
        DeviceType.SENSOR -> Icons.Filled.Sensors
        DeviceType.CAMERA -> Icons.Filled.Videocam
        DeviceType.LOCK -> Icons.Filled.Lock
        DeviceType.CURTAIN -> Icons.Filled.Curtains
        DeviceType.AIR_CONDITIONER -> Icons.Filled.AcUnit
        DeviceType.TV -> Icons.Filled.Tv
        DeviceType.SPEAKER -> Icons.Filled.Speaker
        else -> Icons.Outlined.Info
    }
}
