package com.chrissloan.paw_some.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrissloan.paw_some.presentation.theme.PawsomeTheme

class PawsomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val scaleX = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.SCALE_X,
                1f, 1.2f, 1.25f, 1.2f, 1f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0f
            ).apply {
                duration = 1000L
            }
            val scaleY = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.SCALE_Y,
                1f, 1.2f, 1.25f, 1.2f, 1f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0f
            ).apply {
                duration = 1000L
            }
            val alpha = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.ALPHA,
                1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 0.8f, 0.6f, 0.4f, 0.2f, 0f
            ).apply {
                duration = 1000L
                doOnEnd { splashScreenView.remove() }
            }
            scaleX.start()
            scaleY.start()
            alpha.start()
        }
        super.onCreate(savedInstanceState)
        setContent {
            PawsomeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Breeds : Screen("breeds_screen")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Breeds.route
    ) {
        composable(route = Screen.Breeds.route) {
            AllBreedsScreen()
        }
    }
}

@Composable
fun AllBreedsScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PawsomeTheme {
        Greeting("Android")
    }
}
