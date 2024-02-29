package com.example.synchrony.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.synchrony.MainActivity
import com.example.synchrony.R
import com.example.synchrony.adapter.CountryItemAdapter
import com.example.synchrony.databinding.FragmentHomeBinding
import com.example.synchrony.model.Country
import com.example.synchrony.network.RetrofitInstance
import com.example.synchrony.repository.MainRepository
import com.example.synchrony.viewmodel.MainViewModel
import com.example.synchrony.viewmodel.MainViewModelFactory


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var countryRecyclerView: RecyclerView
    lateinit var countryItemAdapter: CountryItemAdapter
    lateinit var mainViewModel: MainViewModel
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
            mainActivity.binding.toolbarTitle.text = "All Countries"
            mainActivity.binding.toolbarIcon.setImageResource(R.drawable.profile_icon)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryRecyclerView= binding.countriesRv
        countryRecyclerView.layoutManager= LinearLayoutManager(this.requireContext())


        val countryApiService= RetrofitInstance.Instance
        val mainRepository= MainRepository(countryApiService)
        mainViewModel= ViewModelProvider(this, MainViewModelFactory(mainActivity, mainRepository)).get(
            MainViewModel::class.java)

        mainViewModel.getAllCountries()

        mainViewModel.allCountriesLiveData.observe(activity as MainActivity, Observer { list ->
            countryItemAdapter= activity?.let { CountryItemAdapter(list, it.applicationContext, ::countryItemClicked) }!!
            countryRecyclerView.adapter= countryItemAdapter
        })
    }

    override fun onResume() {
        super.onResume()
        mainActivity.binding.toolbarTitle.text= "All Countries"
        mainActivity.binding.toolbarIcon.setImageResource(R.drawable.profile_icon)
    }

    fun countryItemClicked(country: Country) {
        Log.d("raj", "countryItemClicked: "+ (country.name?.official ?: "name is null"))
        val arguments= Bundle()
        arguments.putString("name", country.name?.official)
        arguments.putString("image_url", country.flags?.png)
        val fragment= CountryDetailFragment()
        fragment.arguments= arguments

        mainActivity.openFragment(fragment)
    }
}