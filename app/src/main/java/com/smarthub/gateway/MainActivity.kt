package com.smarthub.gateway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smarthub.gateway.presentation.theme.SmartHubTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * SmartHub主Activity
 * 
 * 作为应用的唯一Activity，托管所有Compose UI
 * 使用Jetpack Navigation管理页面导航
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
                // 临时的欢迎界面，后续会替换为导航系统
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { paddingValues ->
                        WelcomeScreen(modifier = Modifier.padding(paddingValues))
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        Timber.i("MainActivity destroyed")
        super.onDestroy()
    }
}

/**
 * 临时欢迎界面
 * TODO: 后续替换为设备列表页面
 */
@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to SmartHub! 🚀\n\n项目框架已搭建完成\n即将开始开发...",
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    SmartHubTheme {
        WelcomeScreen()
    }
}
