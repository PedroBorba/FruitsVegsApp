package com.pedroborba.fruitsvegsapp.feature.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroborba.fruitsvegsapp.R
import com.pedroborba.fruitsvegsapp.feature.detail.DetailActivity
import com.pedroborba.fruitsvegsapp.model.FruitVegetable
import kotlinx.android.synthetic.main.row_fruitveg_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private var items: List<FruitVegetable>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_fruitveg_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.let { holder.bind(it[position]) }
    }

    fun data(items: List<FruitVegetable>){
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(fruitVeg: FruitVegetable){
            var url = fruitVeg.imageurl

            Glide.with(itemView.context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.imgFruitVeg)

            itemView.txtFruitVegName.text = fruitVeg.name

            itemView.setOnClickListener {
                var it = Intent(itemView.context, DetailActivity::class.java)
                it.putExtra("name", fruitVeg.name)

                itemView.context.startActivity(it)
            }
        }

    }

}