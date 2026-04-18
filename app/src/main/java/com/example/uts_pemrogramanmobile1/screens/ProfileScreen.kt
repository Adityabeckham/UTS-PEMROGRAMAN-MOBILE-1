package com.example.uts_pemrogramanmobile1.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uts_pemrogramanmobile1.ui.theme.BlueGradientBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(username: String, onLogout: () -> Unit) {
    BlueGradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("PROFIL SAYA", fontWeight = FontWeight.Black, letterSpacing = 2.sp) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White.copy(alpha = 0.85f),
                        titleContentColor = Color(0xFF0D47A1)
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Profile Header
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 8.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = username.take(1).uppercase(),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF1976D2)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = username.uppercase(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Mahasiswa Aktif",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Settings Section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        ProfileMenuItem(
                            icon = Icons.Default.Person,
                            title = "Edit Profil",
                            subtitle = "Nama, Email, dan Foto"
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                        ProfileMenuItem(
                            icon = Icons.Default.Settings,
                            title = "Pengaturan",
                            subtitle = "Notifikasi dan Keamanan"
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                        ProfileMenuItem(
                            icon = Icons.Default.Info,
                            title = "Tentang Aplikasi",
                            subtitle = "Versi 1.0.0"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Logout Button
                Button(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("KELUAR AKUN", fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFE3F2FD)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = Color(0xFF1976D2)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
    }
}
