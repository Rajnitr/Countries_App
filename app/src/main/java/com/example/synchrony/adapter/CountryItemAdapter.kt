package com.example.synchrony.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.synchrony.R
import com.example.synchrony.model.Country
import com.squareup.picasso.Picasso

class CountryItemAdapter(val countryList: List<Country>, val context: Context, val countryItemClicked: (country:Country) -> Unit): RecyclerView.Adapter<CountryItemAdapter.CountryItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_country_view, parent, false)
        return CountryItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        val country= countryList.get(position);
        holder.officialNameTv.text = country.name?.official ?: ""
        Picasso.with(context).load(country.flags?.png ?: "").into(holder.countryFlagIV)
        holder.countryItemView.setOnClickListener {
            countryItemClicked(countryList.get(position))
        }
    }

    /*fun updatelist(list: List<Country>) {
        if (countryList.size>0){
            countryList.clear()
            countryList.addAll(list)
        }
        notifyDataSetChanged()
    }*/

    class CountryItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val officialNameTv= view.findViewById<TextView>(R.id.country_name)
        val countryFlagIV= view.findViewById<ImageView>(R.id.country_flag)
        val countryItemView = view.findViewById<RelativeLayout>(R.id.country_item_rl)
    }
}