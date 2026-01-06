package com.example.flit.flitUI.widgets.banners

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flit.domain.model.BannerConfig
import kotlin.random.Random

@Composable
fun BannerWidget(banners: List<BannerConfig>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(banners) { banner ->
            BannerItem(banner)
        }
    }
}

@Composable
private fun BannerItem(banner: BannerConfig) {
    // A palette of beautiful dual-color gradients
    val gradientBrush = remember(banner.title) {
        val gradientPairs = listOf(
            listOf(Color(0xFF6366F1), Color(0xFF8B5CF6)), // Indigo to Violet
            listOf(Color(0xFFEC4899), Color(0xFFF43F5E)), // Pink to Coral
            listOf(Color(0xFF0EA5E9), Color(0xFF2DD4BF)), // Blue to Teal
            listOf(Color(0xFFF59E0B), Color(0xFFD97706)), // Amber to Orange
            listOf(Color(0xFF10B981), Color(0xFF059669)), // Emerald to Green
            listOf(Color(0xFF64748B), Color(0xFF475569)), // Slate to Dark Slate
            listOf(Color(0xFFFF7E5F), Color(0xFFFEB47B)), // Sunset Orange to Peach
            listOf(Color(0xFF764BA2), Color(0xFF667EEA))  // Deep Purple to Blue
        )
        val colors = gradientPairs[Random.nextInt(gradientPairs.size)]
        Brush.linearGradient(colors = colors)
    }

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = banner.title,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = banner.subtitle,
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
