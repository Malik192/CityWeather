package com.example.weatherapp.city
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.App
import com.example.weatherapp.Database
import com.example.weatherapp.R

class CityFragment : Fragment() {
    private lateinit var cities: MutableList<City>
    private lateinit var database: Database
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    interface CityFragmentListener {
        fun onCitySelected(city: City)
        fun onSelectionCleared()
        fun onEmptyCities()
    }

    var listener: CityFragmentListener? = null


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
        recyclerView = view.findViewById(R.id.cities_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cities = database.getAllCities()
        Log.i(TAG, "cities $cities")

        adapter = CityAdapter(cities, this)
        recyclerView.adapter = adapter
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
    fun onCitySelected(city: City) {
        listener?.onCitySelected(city)
    }
    private fun showCreateCityDialog() {
         val createCityFragment = CreateCityDialogFragment()

        createCityFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener
        {


            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() {}


        }
        //afficher la dialog
        fragmentManager?.let { createCityFragment.show(it,"CreateCityDialogFragment") }
    }



    private fun saveCity(city: City) {
        if (database.createCity(city)){
            cities.add(city)
            adapter.notifyDataSetChanged()

        }
        else{
         Toast.makeText(context,
             "could not create city",
             Toast.LENGTH_SHORT).show()
        }


    }
}