package com.cgnature.butterflytracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cgnature.butterflytracker.viewmodel.SightingViewModel

/** Shown when tapping a category that isn't built yet (Birds, Plants). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonScreen(categoryName: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Construction, contentDescription = null, modifier = Modifier.padding(bottom = 16.dp))
            Text("$categoryName tracking isn't built yet", fontWeight = FontWeight.Bold)
            Text("This app currently tracks butterflies only — $categoryName support could be added the same way.")
        }
    }
}

/** Minimal profile/stats screen. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: SightingViewModel, onBack: () -> Unit) {
    val sightings by viewModel.allSightings.collectAsState()
    val speciesCount by viewModel.speciesFoundCount.collectAsState()
    val checklists by viewModel.checklists.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp)) {
            Text("Your stats", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
            Text("Total sightings logged: ${sightings.size}")
            Text("Distinct species found: $speciesCount")
            Text("Checklists (trips) recorded: ${checklists.size}")
        }
    }
}