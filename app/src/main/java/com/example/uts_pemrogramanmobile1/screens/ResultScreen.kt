package com.example.uts_pemrogramanmobile1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uts_pemrogramanmobile1.model.RegistrationData
import com.example.uts_pemrogramanmobile1.ui.theme.BlueGradientBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(data: RegistrationData, onBackToHome: () -> Unit) {
    BlueGradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Status Pendaftaran", fontWeight = FontWeight.ExtraBold) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White.copy(alpha = 0.85f),
                        titleContentColor = Color(0xFF0D47A1)
                    )
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(innerPadding)
                        .padding(vertical = 24.dp),
                    shape = RoundedCornerShape(32.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
                ) {
                    Column(
                        modifier = Modifier.padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(72.dp),
                            tint = Color(0xFF43A047)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "BERHASIL TERDAFTAR!",
                            color = Color(0xFF2E7D32),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            EnhancedResultRow("NAMA LENGKAP", data.name)
                            EnhancedResultRow("EMAIL", data.email)
                            EnhancedResultRow("WHATSAPP", data.phone)
                            EnhancedResultRow("GENDER", data.gender)
                            
                            Surface(
                                color = Color(0xFFE3F2FD),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("TOPIK SEMINAR", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                                    Text(data.seminar, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
                                }
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(
                            onClick = onBackToHome,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                        ) {
                            Text("KEMBALI KE BERANDA", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EnhancedResultRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label, 
            fontSize = 11.sp, 
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = value, 
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}
