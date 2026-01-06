package com.example.flit.data.datasource

import com.example.flit.domain.model.ListWidgetConfig
import kotlinx.coroutines.delay

object ListDataSource{

    suspend fun getList(instanceId:String):List<ListWidgetConfig>{
        delay(1000)

        return when (instanceId){
             "movies" -> listOf(
                 ListWidgetConfig("The Shawshank Redemption", "Frank Darabont"),
                 ListWidgetConfig("The Godfather", "Francis Ford Coppola"),
                 ListWidgetConfig("The Dark Knight", "Christopher Nolan")
             )
            "shows" -> listOf(
                ListWidgetConfig("Game of Thrones", "George R. R. Martin"),
                ListWidgetConfig("Stranger Things", "The Duffer Brothers"),
                ListWidgetConfig("The Walking Dead", "Robert Kirkman")

            )
            else -> emptyList()

        }
    }
}