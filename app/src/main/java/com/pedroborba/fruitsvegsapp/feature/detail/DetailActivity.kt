package com.pedroborba.fruitsvegsapp.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.pedroborba.fruitsvegsapp.R
import com.pedroborba.fruitsvegsapp.api.RetrofitManager
import com.pedroborba.fruitsvegsapp.model.FindResult
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_list.progressBar
import kotlinx.android.synthetic.main.row_fruitveg_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var name = intent.getStringExtra("name")
        if(name.isNotEmpty()){
            loadDetail(name)
        }
    }

    fun loadDetail(name: String){
        var call : Call<FindResult>?

        call = RetrofitManager
            .getFruitsVegsService()
            .getDetailedFruitVeg(name)

        call.enqueue(object : Callback<FindResult> {

            override fun onFailure(call: Call<FindResult>, t: Throwable) {
                Log.e("@@@@@ Erro ao se comunicar com a API @@@@@@", "Error", t)
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        var fruit = it.items[0]
                        var url = fruit.imageurl

                        Glide.with(applicationContext)
                            .load(url)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imgFruitVegBig)

                        txtName.text = fruit.name
                        txtBot.text = fruit.botanicalNames
                        txtOtherNames.text = fruit.otherNames
                        txtDescription.text = fruit.description
                    }
                }
            }

        })
    }
}