package com.tc.countrieslist.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tc.countrieslist.R
import com.tc.countrieslist.data.model.countries.CountriesItemModel
import com.tc.countrieslist.databinding.ItemCountryBinding

class CountryAdapter(private val data: ArrayList<CountriesItemModel>) :
    RecyclerView.Adapter<CountryAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCountryBinding.bind(view)

        fun setupUI(countryData: CountriesItemModel) {
            binding.nameTextView.text = "${countryData.name}, ${countryData.region}"
            binding.codeTextView.text = countryData.code
            binding.capitalTextView.text = countryData.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: CountryAdapter.CustomViewHolder, position: Int) {
        val senderData = data[position]
        holder.setupUI(senderData)

    }

    override fun getItemCount() = data.size

}
