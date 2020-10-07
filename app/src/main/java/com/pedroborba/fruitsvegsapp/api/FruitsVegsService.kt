package com.pedroborba.fruitsvegsapp.api
import com.pedroborba.fruitsvegsapp.model.FindResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FruitsVegsService {

    @GET("tfvjsonapi.php")
    fun listAll(
        @Query("search") search: String = "all") : Call<FindResult>

    @GET("tfvjsonapi.php")
    fun getDetailedFruitVeg(
        @Query("tfvitem") itemName: String) : Call<FindResult>


}