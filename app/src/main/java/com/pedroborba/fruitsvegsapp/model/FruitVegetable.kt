package com.pedroborba.fruitsvegsapp.model
import com.google.gson.annotations.SerializedName

data class FindResult(
    @SerializedName("results")
    val items: List<FruitVegetable>
)

data class FruitVegetable (
    @SerializedName("tfvname")
    val name: String = "",
    @SerializedName("botname")
    val botanicalNames: String = "",
    @SerializedName("othname")
    val otherNames: String = "",
    val imageurl: String = "",
    val description: String = ""
)