package com.example.flit.flitUI.dashboard

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flit.core.UiState
import com.example.flit.core.WidgetType
import com.example.flit.domain.model.BannerConfig
import com.example.flit.flitUI.widgets.banners.BannerWidget
import com.example.flit.flitUI.widgets.lists.ListWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "FLIT",
                        fontWeight = FontWeight.Black,
                        letterSpacing = 4.sp,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Crossfade(targetState = state, label = "dashboard_state") { currentState ->
                when (currentState) {
                    UiState.Loading -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                strokeWidth = 3.dp
                            )
                        }
                    }

                    is UiState.Error -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Failed to load dashboard",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    is UiState.Success -> {
                        val widgets = currentState.data
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(32.dp),
                            contentPadding = PaddingValues(bottom = 24.dp, top = 16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(widgets) { widget ->
                                when (widget.type) {
                                    WidgetType.BANNER -> {
                                        val banners by produceState<List<BannerConfig>>(
                                            initialValue = emptyList(),
                                            key1 = widget.instanceId
                                        ) {
                                            value = viewModel.getBanners(widget.instanceId)
                                        }
                                        if (banners.isNotEmpty()) {
                                            BannerWidget(banners = banners)
                                        }
                                    }

                                    WidgetType.LIST -> {
                                        ListWidget(instanceId = widget.instanceId)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
