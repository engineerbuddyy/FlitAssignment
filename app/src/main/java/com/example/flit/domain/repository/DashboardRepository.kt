package com.example.flit.domain.repository

import com.example.flit.domain.model.BannerConfig
import com.example.flit.domain.model.ListWidgetConfig
import com.example.flit.domain.model.WidgetDescriptor

interface DashboardRepository{
    suspend fun getDashboardMetadata(): List<WidgetDescriptor>
    suspend fun getBanners(instanceId:String):List<BannerConfig>
    suspend fun getList(instanceId:String):List<ListWidgetConfig>
}