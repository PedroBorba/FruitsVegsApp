package com.pedroborba.fruitsvegsapp.feature.list

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedroborba.fruitsvegsapp.R
import com.pedroborba.fruitsvegsapp.api.RetrofitManager
import com.pedroborba.fruitsvegsapp.model.FindResult
import com.pedroborba.fruitsvegsapp.model.FruitVegetable
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class ListActivity : AppCompatActivity() {

    private val adapter = ListAdapter()
    private var lista : List<FruitVegetable>? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initUI()
    }

    private fun initUI() {
        rvFruitVeg.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = this@ListActivity.adapter
        }

        btnSearch.setOnClickListener {
            findFruitVeg()
        }

        loadFruitsVegs()
    }

    fun loadFruitsVegs(){
        if(isDeviceConnected()){
            progressBar.visibility = View.VISIBLE

            var call : Call<FindResult>?

            call = RetrofitManager
                .getFruitsVegsService()
                .listAll()

            call.enqueue(object : Callback<FindResult> {

                override fun onFailure(call: Call<FindResult>, t: Throwable) {
                    Log.e("@@@@@ Erro ao se comunicar com a API @@@@@@", "Error", t)
                    progressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            lista = it.items
                            adapter.data(lista!!)
                        }
                    }
                    progressBar.visibility = View.GONE
                }

            })
        }
    }

    fun findFruitVeg(){
        if(lista != null && lista!!.isNotEmpty() && edtFruitVegName.text.isNotBlank()){
            progressBar.visibility = View.VISIBLE
            var filterList = lista!!.filter { it -> it.name.equals(edtFruitVegName.text.toString(), ignoreCase = true)  }
            if(filterList.isNotEmpty()){
                adapter.data(filterList)
            } else {
                adapter.data(lista!!)
                Toast.makeText(this, "No results found!", Toast.LENGTH_LONG)
            }
            progressBar.visibility = View.GONE
        }
    }

    fun isDeviceConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected;
    }
}