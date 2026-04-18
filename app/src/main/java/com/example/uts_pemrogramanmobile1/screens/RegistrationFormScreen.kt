package com.example.uts_pemrogramanmobile1.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uts_pemrogramanmobile1.model.RegistrationData
import com.example.uts_pemrogramanmobile1.ui.theme.BlueGradientBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationFormScreen(onSubmit: (RegistrationData) -> Unit, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Laki-laki") }
    val seminarList = listOf(
        "Android Development with Compose",
        "Introduction to AI",
        "Cyber Security Fundamentals",
        "Cloud Computing with AWS",
        "UI/UX Design Trends 2024"
    )
    var selectedSeminar by remember { mutableStateOf(seminarList[0]) }
    var agreement by remember { mutableStateOf(false) }
    var hasAttemptedSubmit by remember { mutableStateOf(false) }
    
    var showDialog by remember { mutableStateOf(false) }
    
    // Validation logic
    val nameError = if (hasAttemptedSubmit && name.isEmpty()) "Nama wajib diisi" else null
    val emailError = if (hasAttemptedSubmit) {
        if (email.isEmpty()) "Email wajib diisi" 
        else if (!email.contains("@")) "Email harus mengandung '@'" else null
    } else null

    val phoneError = if (hasAttemptedSubmit) {
        if (phone.isEmpty()) "Nomor HP wajib diisi"
        else if (!phone.all { it.isDigit() }) "Hanya boleh angka"
        else if (phone.length !in 10..13) "Panjang 10-13 digit"
        else if (!phone.startsWith("08")) "Harus diawali dengan 08" else null
    } else null
    
    val agreementError = if (hasAttemptedSubmit && !agreement) "Persetujuan harus dicentang" else null

    val isFormValid = name.isNotEmpty() && 
                     email.isNotEmpty() && email.contains("@") && 
                     phone.isNotEmpty() && phone.all { it.isDigit() } && 
                     phone.length in 10..13 && phone.startsWith("08") && 
                     agreement

    BlueGradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Daftar Seminar", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF0D47A1))
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White.copy(alpha = 0.85f),
                        titleContentColor = Color(0xFF0D47A1)
                    )
                )
            }
        ) { innerPadding ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Explicitly use the scope to avoid "BoxWithConstraints scope is not used" error
                val isWideScreen = this.maxWidth > 600.dp
                
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(if (isWideScreen) 0.7f else 0.92f)
                            .padding(bottom = 32.dp),
                        shape = RoundedCornerShape(28.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = "Informasi Pendaftaran",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFF0D47A1),
                                fontWeight = FontWeight.Bold
                            )
                            
                            HorizontalDivider(thickness = 2.dp, color = Color(0xFFBBDEFB))

                            // Nama
                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text("Nama Lengkap") },
                                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF1976D2)) },
                                modifier = Modifier.fillMaxWidth(),
                                isError = nameError != null,
                                supportingText = { 
                                    if (nameError != null) {
                                        Text(nameError, color = MaterialTheme.colorScheme.error)
                                    }
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp)
                            )

                            // Email
                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("Email Address") },
                                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFF1976D2)) },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                isError = emailError != null,
                                supportingText = { 
                                    if (emailError != null) {
                                        Text(emailError, color = MaterialTheme.colorScheme.error)
                                    }
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp)
                            )

                            // Phone
                            OutlinedTextField(
                                value = phone,
                                onValueChange = { phone = it },
                                label = { Text("Nomor WhatsApp") },
                                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF1976D2)) },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                isError = phoneError != null,
                                supportingText = { 
                                    if (phoneError != null) {
                                        Text(phoneError, color = MaterialTheme.colorScheme.error)
                                    }
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp)
                            )

                            // Gender
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("Jenis Kelamin", fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
                                val radioOptions = listOf("Laki-laki", "Perempuan")
                                Row(
                                    modifier = Modifier.selectableGroup().fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    radioOptions.forEach { text ->
                                        Surface(
                                            modifier = Modifier
                                                .weight(1f)
                                                .selectable(
                                                    selected = (text == gender),
                                                    onClick = { gender = text },
                                                    role = Role.RadioButton
                                                ),
                                            shape = RoundedCornerShape(12.dp),
                                            color = if (text == gender) Color(0xFFE3F2FD) else Color.Transparent,
                                            border = if (text == gender) BorderStroke(1.dp, Color(0xFF1976D2)) else BorderStroke(1.dp, Color.LightGray)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(8.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(selected = (text == gender), onClick = null)
                                                Text(text = text, modifier = Modifier.padding(start = 4.dp), fontSize = 14.sp)
                                            }
                                        }
                                    }
                                }
                            }

                            // Seminar Spinner
                            var expanded by remember { mutableStateOf(false) }
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded }
                            ) {
                                OutlinedTextField(
                                    value = selectedSeminar,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Pilih Topik Seminar") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                    modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryNotEditable),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    seminarList.forEach { item ->
                                        DropdownMenuItem(
                                            text = { Text(item) },
                                            onClick = {
                                                selectedSeminar = item
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            // Agreement
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { agreement = !agreement }
                                        .padding(vertical = 4.dp)
                                ) {
                                    Checkbox(
                                        checked = agreement,
                                        onCheckedChange = { agreement = it },
                                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1976D2))
                                    )
                                    Text(
                                        "Saya menjamin data yang diisi benar",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = if (agreementError != null) MaterialTheme.colorScheme.error else Color.DarkGray
                                    )
                                }
                                if (agreementError != null) {
                                    Text(
                                        text = agreementError,
                                        color = MaterialTheme.colorScheme.error,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    hasAttemptedSubmit = true
                                    if (isFormValid) {
                                        showDialog = true
                                    }
                                },
                                modifier = Modifier.fillMaxWidth().height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                            ) {
                                Text("DAFTAR SEKARANG", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Konfirmasi Data", fontWeight = FontWeight.Bold) },
            text = { Text("Pastikan semua informasi sudah benar sebelum melanjutkan pendaftaran.") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onSubmit(RegistrationData(name, email, phone, gender, selectedSeminar))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Ya, Sudah Benar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cek Kembali", color = Color.Gray)
                }
            },
            shape = RoundedCornerShape(28.dp),
            containerColor = Color.White
        )
    }
}
