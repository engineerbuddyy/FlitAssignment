package com.example.flit.flitUI.widgets.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flit.core.UiState
import kotlin.random.Random

@Composable
fun ListWidget(instanceId: String) {
    val viewModel = remember { ListWidgetViewModel(instanceId) }
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = instanceId.replaceFirstChar { it.uppercase() },
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface,
            letterSpacing = (-0.5).sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (state) {
            UiState.Loading -> {
                Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            is UiState.Error -> {
                Text(
                    "Error loading content", 
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }

            is UiState.Success -> {
                val items = (state as UiState.Success).data
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items.forEachIndexed { index, item ->
                        ListItemCard(name = item.name, surname = item.surname, index = index)
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItemCard(name: String, surname: String, index: Int) {
    // Generate a consistent but distinct accent color for the avatar
    val accentColor = remember(name) {
        val palette = listOf(
            Color(0xFF6366F1), // Indigo
            Color(0xFFEC4899), // Pink
            Color(0xFF0EA5E9), // Sky Blue
            Color(0xFF10B981), // Emerald
            Color(0xFFF59E0B)  // Amber
        )
        palette[index % palette.size]
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(22.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = surname,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
