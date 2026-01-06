package com.example.flit.data.repository

import com.example.flit.core.WidgetType
import com.example.flit.data.datasource.BannerDataSource
import com.example.flit.data.datasource.ListDataSource
import com.example.flit.domain.model.BannerConfig
import com.example.flit.domain.model.ListWidgetConfig
import com.example.flit.domain.model.WidgetDescriptor
import com.example.flit.domain.repository.DashboardRepository
import kotlinx.coroutines.delay

class DashboardRepositoryImpl: DashboardRepository {
    override suspend fun getDashboardMetadata(): List<WidgetDescriptor> {
        delay(500)
        return listOf(
            WidgetDescriptor(WidgetType.BANNER, "pokemon"),
            WidgetDescriptor(WidgetType.BANNER, "cars"),
            WidgetDescriptor(WidgetType.BANNER, "bikes"),
            WidgetDescriptor(WidgetType.LIST, "movies"),
            WidgetDescriptor(WidgetType.LIST, "shows")
        )
    }

    override suspend fun getBanners(instanceId: String): List<BannerConfig> {
        return BannerDataSource.getBanners(instanceId)
    }

    override suspend fun getList(instanceId: String): List<ListWidgetConfig> {
        return ListDataSource.getList(instanceId)

    }

}