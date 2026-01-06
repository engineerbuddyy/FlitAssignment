package com.example.flit.data.datasource

import com.example.flit.domain.model.BannerConfig

object BannerDataSource {

    fun getBanners(instanceId:String):List<BannerConfig>{
        return when(instanceId){
            "pokemon"->listOf(
                BannerConfig("Pikachu","Electric"),
                BannerConfig("Charizard","Fire"),
                BannerConfig("Bulbasaur","Grass"),
                BannerConfig("Squirtle","Water")
            )
            "cars"->listOf(
                BannerConfig("Tesla","Electric"),
                BannerConfig("BMW","Luxury"),
                BannerConfig("Mercedes","Luxury"),
                BannerConfig("Audi","Luxury")
            )
            "bikes"->listOf(
                BannerConfig("Ducati","Sports"),
                BannerConfig("Harley-Davidson","Sports"),
                BannerConfig("Kawasaki","Sports"),
                BannerConfig("Yamaha","Sports")
            )
            else->emptyList()

        }

    }

}