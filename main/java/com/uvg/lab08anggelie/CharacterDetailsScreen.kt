package com.uvg.lab08anggelie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

internal val characterDatabase = CharacterDb()

@Composable
internal fun CharacterDetailsScreen(navigateBack: () -> Unit, characterId: Int) {
    Column(Modifier.fillMaxSize()) {
        CharacterAppBar(onNavigateBack = navigateBack)
        Spacer(modifier = Modifier.height(16.dp))
        CharacterDetails(characterId = characterId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterAppBar(onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { Text("Character Info") },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Go back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )
}

@Composable
private fun CharacterDetails(characterId: Int) {
    val character = characterDatabase.getCharacterById(characterId)
    Row {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineMedium,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(32.dp))
            CharacterInfoRow("Species", character.species)
            CharacterInfoRow("Status", character.status)
            CharacterInfoRow("Gender", character.gender)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Anggelie Velásquez 221181",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun AsyncImage() {
}

@Composable
private fun CharacterInfoRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
