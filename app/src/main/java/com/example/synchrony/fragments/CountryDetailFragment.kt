package com.example.synchrony.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments
import androidx.lifecycle.Observer
import com.example.synchrony.MainActivity
import com.example.synchrony.R
import com.example.synchrony.databinding.FragmentCountryDetailBinding
import com.example.synchrony.databinding.FragmentHomeBinding
import com.example.synchrony.model.CountryDetail
import com.squareup.picasso.Picasso


class CountryDetailFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentCountryDetailBinding
    lateinit var countryName: String
    var argument: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
        argument= arguments
        mainActivity.binding.toolbarTitle.text= "Country Details"
        mainActivity.binding.toolbarIcon.setImageResource(R.drawable.back_icon)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCountryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         countryName= argument?.getString("name").toString()
        val image_url= argument?.getString("image_url")
        //set country name and image
        Picasso.with(mainActivity).load(image_url).into(binding.countryDetailIv)
        binding.countryDetailName.text= countryName
        if (countryName != null) {
            mainActivity.mainViewModel.getSpecificCountries(countryName)
            mainActivity.mainViewModel.specificCountriesLiveData.observe(mainActivity, Observer {countryList ->
                var countryDetail: CountryDetail = countryList.get(0)
                for (item in countryList){
                    if (item.name?.official?.equals(countryName, true) == true) {
                        countryDetail= item
                    }
                }
                var capital=""
                var borders=""
                var timezones=""
                var continents=""

                for (item in countryDetail.capital) {
                    capital+=item + " "
                }
                for (item in countryDetail.borders) {
                    borders+=item + " "
                }
                for (item in countryDetail.timezones) {
                    timezones+=item + " "
                }
                for (item in countryDetail.continents) {
                    continents+=item + " "
                }

                binding.capital.text= capital
                binding.Region.text= countryDetail.region
                binding.SubRegion.text= countryDetail.subregion
                binding.area.text= countryDetail.area.toString()
                binding.population.text= countryDetail.population.toString()
                binding.maps.text= countryDetail.maps?.googleMaps.toString()
                binding.timezones.text= timezones
                binding.continents.text= continents
                binding.startOfWeek.text= countryDetail.startOfWeek
                binding.maps.setOnClickListener {
                    var intent= Intent(Intent.ACTION_VIEW, Uri.parse(countryDetail.maps?.googleMaps))
                    mainActivity.startActivity(intent)
                }



            })
        }


    }
}