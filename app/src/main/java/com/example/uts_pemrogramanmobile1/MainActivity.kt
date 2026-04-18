package com.example.uts_pemrogramanmobile1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uts_pemrogramanmobile1.model.RegistrationData
import com.example.uts_pemrogramanmobile1.screens.*
import com.example.uts_pemrogramanmobile1.ui.theme.UTSPEMROGRAMANMOBILE1Theme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UTSPEMROGRAMANMOBILE1Theme {
                AppNavigation()
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home/{username}", "Home", Icons.Default.Home)
    object Form : Screen("form", "Daftar", Icons.Default.Add)
    object Profile : Screen("profile/{username}", "Profil", Icons.Default.Person)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val showBottomBar = currentDestination?.route?.let { route ->
        route.startsWith("home") || route == "form" || route.startsWith("profile")
    } ?: false

    var loggedInUsername by remember { mutableStateOf("User") }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                    tonalElevation = 8.dp
                ) {
                    val items = listOf(
                        Screen.Home,
                        Screen.Form,
                        Screen.Profile
                    )
                    items.forEach { screen ->
                        val routeWithParams = if (screen == Screen.Form) screen.route else {
                            screen.route.replace("{username}", loggedInUsername)
                        }
                        
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(routeWithParams) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController, 
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = { username ->
                        loggedInUsername = username
                        navController.navigate("home/$username") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate("register")
                    }
                )
            }
            
            composable("register") {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate("login")
                    }
                )
            }

            composable(
                route = Screen.Home.route,
                arguments = listOf(navArgument("username") { type = NavType.StringType })
            ) { backStackEntry ->
                val username = backStackEntry.arguments?.getString("username") ?: "User"
                HomeScreen(
                    username = username,
                    onNavigateToForm = {
                        navController.navigate("form")
                    }
                )
            }

            composable(Screen.Form.route) {
                RegistrationFormScreen(
                    onSubmit = { data ->
                        val encodedName = URLEncoder.encode(data.name, StandardCharsets.UTF_8.toString())
                        val encodedEmail = URLEncoder.encode(data.email, StandardCharsets.UTF_8.toString())
                        val encodedPhone = URLEncoder.encode(data.phone, StandardCharsets.UTF_8.toString())
                        val encodedGender = URLEncoder.encode(data.gender, StandardCharsets.UTF_8.toString())
                        val encodedSeminar = URLEncoder.encode(data.seminar, StandardCharsets.UTF_8.toString())
                        navController.navigate("result/$encodedName/$encodedEmail/$encodedPhone/$encodedGender/$encodedSeminar")
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.Profile.route,
                arguments = listOf(navArgument("username") { type = NavType.StringType })
            ) { backStackEntry ->
                val username = backStackEntry.arguments?.getString("username") ?: "User"
                ProfileScreen(
                    username = username,
                    onLogout = {
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable(
                route = "result/{name}/{email}/{phone}/{gender}/{seminar}",
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("email") { type = NavType.StringType },
                    navArgument("phone") { type = NavType.StringType },
                    navArgument("gender") { type = NavType.StringType },
                    navArgument("seminar") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val data = RegistrationData(
                    name = backStackEntry.arguments?.getString("name") ?: "",
                    email = backStackEntry.arguments?.getString("email") ?: "",
                    phone = backStackEntry.arguments?.getString("phone") ?: "",
                    gender = backStackEntry.arguments?.getString("gender") ?: "",
                    seminar = backStackEntry.arguments?.getString("seminar") ?: ""
                )
                ResultScreen(
                    data = data,
                    onBackToHome = {
                        navController.navigate("home/$loggedInUsername") {
                            popUpTo("home/{username}") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
