# Seminar Registration App - UTS Pemrograman Mobile 1

Aplikasi Pendaftaran Seminar Mahasiswa berbasis Android yang dibangun menggunakan **Jetpack Compose** dan **Material Design 3**. Aplikasi ini dirancang dengan antarmuka modern menggunakan tema *Glassmorphism* dan gradasi warna biru yang konsisten.

## 🚀 Fitur Utama
- **Autentikasi**: Fitur Login dan Register untuk keamanan akses.
- **Dashboard Utama**: Menampilkan profil singkat pengguna dan kartu informasi event.
- **Form Pendaftaran Seminar**:
  - Input: Nama, Email, No. WhatsApp, Jenis Kelamin (Button-style), dan Topik Seminar (Dropdown).
  - **Validasi Real-time**: Error muncul saat mengetik jika data tidak valid (Email, Phone, Required Fields).
  - **Responsif**: Layout menyesuaikan ukuran layar (HP & Tablet).
- **Dialog Konfirmasi**: Memastikan data sudah benar sebelum dikirim.
- **Halaman Hasil**: Ringkasan data pendaftaran setelah berhasil submit.
- **Profil & Pengaturan**: Dilengkapi dengan menu pengaturan profil dan fitur **Logout**.
- **Bottom Navigation**: Navigasi cepat antara Home, Form Daftar, dan Profil.

## 🛠️ Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Navigation**: Jetpack Navigation Compose
- **Design Pattern**: Single Activity Architecture

## 🔑 Akun Demo (Hardcoded)
- **Username**: `aditya`
- **Password**: `adit123`

## 🎥 Video Penjelasan (Link)
[Klik di sini untuk menonton video penjelasan kode dan UI](https://youtu.be/Sn1ogaT7uBE)

---

## 📸 Tampilan Aplikasi
Fitur Login
<img width="199" height="377" alt="image" src="https://github.com/user-attachments/assets/2a7c0a35-51a6-46c2-8ca7-6703ca61f709" />

Fitur  Register
<img width="215" height="374" alt="image" src="https://github.com/user-attachments/assets/0839f95d-9ef3-492b-abf4-cde4eb385525" />

Fitur Pendaftaraan Seminar
<img width="209" height="374" alt="image" src="https://github.com/user-attachments/assets/d00d4341-b959-4810-be3e-09a7c052717e" />


---

## 📂 Struktur Proyek
- `MainActivity.kt`: Pusat kontrol navigasi aplikasi.
- `model/`: Data class untuk menampung informasi pendaftaran.
- `screens/`: Folder berisi semua halaman UI (Login, Register, Home, Form, Result, Profile).
- `ui/theme/`: Konfigurasi tema, warna, tipografi, dan background gradasi.

## ⚙️ Cara Menjalankan
1. Clone repository ini.
2. Buka di **Android Studio Ladybug** atau versi terbaru.
3. Lakukan **Gradle Sync**.
4. Run pada Emulator atau Perangkat Fisik (Android API 24+).

---
**Create by:** [Aditya Beckham]

