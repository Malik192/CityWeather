package com.example.weatherapp.city

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.weatherapp.App
import com.example.weatherapp.Database
import com.example.weatherapp.R

class CityFragment : Fragment() {
    private lateinit var cities: MutableList<City>
    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //recuperer une instance de bdd
        database= App.database
        cities = mutableListOf()
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_city, container, false)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_city,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCreateCityDialog() {
         val createCityFragment = CreateCityDialogFragment()

        createCityFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener
        {
            fun onDialogPosClick(cityName: String) {
               saveCity(City(cityName))
            }

            fun onDialogNegClick(){}
            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() {}


        }
        //afficher la dialog
        fragmentManager?.let { createCityFragment.show(it,"CreateCityDialogFragment") }
    }



    private fun saveCity(city: City) {
        if (database.create_city(city)){
            cities.add(city)

        }
        else{
         Toast.makeText(context,
             "could not create city",
             Toast.LENGTH_SHORT).show()
        }


    }
}