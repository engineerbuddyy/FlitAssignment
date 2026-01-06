package com.example.flit.domain.model

import com.example.flit.core.WidgetType

data class WidgetDescriptor(
    val type: WidgetType,
    val instanceId: String
)