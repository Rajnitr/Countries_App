package com.example.synchrony

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.synchrony.databinding.ActivityMainBinding
import com.example.synchrony.fragments.HomeFragment
import com.example.synchrony.fragments.ProfileFragment
import com.example.synchrony.network.CountryApiService
import com.example.synchrony.network.RetrofitInstance
import com.example.synchrony.repository.MainRepository
import com.example.synchrony.viewmodel.MainViewModel
import com.example.synchrony.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.top_toolbar))
        binding.toolbarIcon.setOnClickListener {
            if (binding.toolbarTitle.text.equals("All Countries")) {
                openFragment(ProfileFragment())
            }
            else {
                onBackPressed()
            }
        }
        val countryApiService= RetrofitInstance.Instance
        val mainRepository= MainRepository(countryApiService)
        mainViewModel= ViewModelProvider(this, MainViewModelFactory(this, mainRepository)).get(MainViewModel::class.java)
        openFragment(HomeFragment())
    }

     fun openFragment(fragment : Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
         if (fragment is HomeFragment) {
             fragmentTransaction.replace(R.id.fragment_container, fragment)
         }
         else {
             fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
         }
        fragmentTransaction.commit()
    }
}