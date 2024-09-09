package com.uvg.lab08anggelie

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Box as LayoutBox

@Parcelize
data class CharacterDescriptionDestination(val id: Int) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterDescriptionDestination> {
        override fun createFromParcel(parcel: Parcel): CharacterDescriptionDestination {
            return CharacterDescriptionDestination(parcel)
        }

        override fun newArray(size: Int): Array<CharacterDescriptionDestination?> {
            return arrayOfNulls(size)
        }
    }
}

annotation class Parcelize

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object CharacterList : Screen("character_list")
    data object CharacterDetails : Screen("character_details/{id}") {
        fun createRoute(id: Int) = "character_details/$id"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppContent() {
    val navController = rememberNavController()
    Scaffold {
        NavigationComponent(navController = navController)
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            WelcomeScreen(onEnterApp = { navController.navigate(Screen.CharacterList.route) })
        }
        composable(Screen.CharacterList.route) {
            CharacterListScreen(
                onCharacterSelected = { id ->
                    navController.navigate(Screen.CharacterDetails.createRoute(id))
                }
            )
        }
        composable(Screen.CharacterDetails.route) { backStackEntry: NavBackStackEntry ->
            (backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0).also {
                Box {
                    LayoutBox { }
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onEnterApp: () -> Unit) {
    LayoutBox(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(280.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onEnterApp,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.width(200.dp)
            ) {
                Text("Empezar")
            }
        }
        Text(
            text = "Anggelie Velásquez 221181",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
fun CharacterListScreen(onCharacterSelected: (Int) -> Unit) {
    // Aquí agregarás la lógica para mostrar el listado de personajes
    // y llamar a onCharacterSelected(id) cuando se selecciona un personaje.
}

@Composable
fun CharacterDetailsScreen(characterId: Int, navigateBack: () -> Unit) {
    // Aquí agregarás la lógica para mostrar los detalles del personaje
    // utilizando el characterId para obtener la información correspondiente.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WelcomeScreen(onEnterApp = {})
}
