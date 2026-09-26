package com.cgnature.butterflytracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cgnature.butterflytracker.data.ButterflySpecies
import com.cgnature.butterflytracker.data.ChhattisgarhButterflies
import com.cgnature.butterflytracker.ui.theme.BarkBrown
import com.cgnature.butterflytracker.ui.theme.ForestGreen
import com.cgnature.butterflytracker.viewmodel.SightingViewModel

/**
 * "Butterflies" checklist: every species in the seed list, ticked off once
 * you've logged at least one sighting of it. Tapping the checklist card on
 * the dashboard opens this screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButterflyChecklistScreen(
    viewModel: SightingViewModel,
    onBack: () -> Unit
) {
    val sightings by viewModel.allSightings.collectAsState()
    val allSpecies by viewModel.speciesList.collectAsState()
    val foundNames = remember(sightings) {
        sightings.filter { it.category == com.cgnature.butterflytracker.data.Category.BUTTERFLY.name }
            .map { it.commonName }.toSet()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Butterfly checklist") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Text(
                "${foundNames.size} of ${allSpecies.size} species found",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                color = BarkBrown
            )
            LazyColumn(contentPadding = PaddingValues(12.dp)) {
                items(allSpecies) { species ->
                    ChecklistSpeciesRow(species = species, found = species.commonName in foundNames)
                }
            }
        }
    }

}

@Composable
private fun ChecklistSpeciesRow(species: ButterflySpecies, found: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(species.commonName, fontWeight = FontWeight.Bold, color = BarkBrown)
                Text(species.scientificName, color = BarkBrown.copy(alpha = 0.6f))
            }
            if (found) {
                Icon(Icons.Filled.Check, contentDescription = "Found", tint = ForestGreen)
            }
        }
    }
}