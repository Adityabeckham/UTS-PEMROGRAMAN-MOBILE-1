package com.example.uts_pemrogramanmobile1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uts_pemrogramanmobile1.ui.theme.BlueGradientBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(username: String, onNavigateToForm: () -> Unit) {
    BlueGradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("DASHBOARD", fontWeight = FontWeight.Black, letterSpacing = 2.sp) },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0xFF0D47A1))
                        }
                    },
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
                // Header Profile Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(64.dp),
                            shape = CircleShape,
                            color = Color(0xFFBBDEFB)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = username.take(1).uppercase(),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1976D2)
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column {
                            Text(text = "Selamat Datang,", fontSize = 14.sp, color = Color.Gray)
                            Text(
                                text = username.uppercase(),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF0D47A1)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Info Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.9f))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFBC02D))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Event Unggulan Minggu Ini",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Jangan lewatkan kesempatan untuk belajar Android Development langsung dari para ahli!",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Bottom Action Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Siap untuk memulai?",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Daftarkan dirimu ke seminar pilihan sekarang juga.",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                        )
                        
                        Button(
                            onClick = onNavigateToForm,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                        ) {
                            Text("DAFTAR SEKARANG", fontSize = 16.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
