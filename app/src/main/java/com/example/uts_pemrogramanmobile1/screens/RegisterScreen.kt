package com.example.uts_pemrogramanmobile1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uts_pemrogramanmobile1.ui.theme.BlueGradientBackground

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, onNavigateToLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    BlueGradientBackground {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(vertical = 32.dp),
                shape = RoundedCornerShape(32.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(80.dp),
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFFE1F5FE)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.padding(20.dp).fillMaxSize(),
                            tint = Color(0xFF1976D2)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Daftar Akun",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0D47A1)
                    )
                    Text(
                        text = "Lengkapi data untuk mendaftar",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            error = ""
                        },
                        label = { Text("Username") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF1976D2)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1976D2),
                            unfocusedBorderColor = Color(0xFFBBDEFB)
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            error = ""
                        },
                        label = { Text("Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF1976D2)) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1976D2),
                            unfocusedBorderColor = Color(0xFFBBDEFB)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            error = ""
                        },
                        label = { Text("Konfirmasi Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF1976D2)) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1976D2),
                            unfocusedBorderColor = Color(0xFFBBDEFB)
                        )
                    )

                    if (error.isNotEmpty()) {
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            if (username.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                                onRegisterSuccess()
                            } else if (password != confirmPassword) {
                                error = "Password tidak cocok"
                            } else {
                                error = "Harap isi semua bidang"
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                    ) {
                        Text("DAFTAR", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sudah punya akun?", style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
                        TextButton(onClick = onNavigateToLogin) {
                            Text("Masuk", fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                        }
                    }
                }
            }
        }
    }
}
