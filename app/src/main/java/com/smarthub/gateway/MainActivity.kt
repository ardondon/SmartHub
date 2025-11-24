package com.smarthub.gateway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.smarthub.gateway.presentation.devices.DevicesScreen
import com.smarthub.gateway.presentation.theme.SmartHubTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * SmartHub主Activity
 * 
 * 作为应用的唯一Activity，托管所有Compose UI
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Timber.i("MainActivity created")
        
        // 启用边到边显示
        enableEdgeToEdge()
        
        setContent {
            SmartHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DevicesScreen()
                }
            }
        }
    }
}
