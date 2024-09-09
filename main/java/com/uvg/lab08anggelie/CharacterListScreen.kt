package com.uvg.lab08anggelie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterListAppBar() {
    TopAppBar(
        title = { Text("Character Gallery") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun CharacterList(onCharacterSelected: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(characterDatabase.getAllCharacters()) { personaje: Character ->
            CharacterListItem(character = personaje, onCharacterSelected = onCharacterSelected)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CharacterListItem(character: Character, onCharacterSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCharacterSelected(character.id) }
            .padding(8.dp)
    ) {
        Column {
            Box {
                AsyncImage()
            }
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "${character.species} - ${character.status}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Anggelie Velásquez 221181",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


