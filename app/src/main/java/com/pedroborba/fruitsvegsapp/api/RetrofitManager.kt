package com.pedroborba.fruitsvegsapp.api

import com.pedroborba.fruitsvegsapp.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private val instance = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getFruitsVegsService() = instance.create(FruitsVegsService::class.java)
}