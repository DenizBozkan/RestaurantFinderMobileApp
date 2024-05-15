package com.example.restaurant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.R
import com.example.restaurant.RestaurantAdapter
import com.example.restaurant.Restaurants
import java.util.*
import kotlin.collections.ArrayList

class SettingsFragment: Fragment(R.layout.third_fragment) {
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<Restaurants>()
    private lateinit var adapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.third_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.search_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        addDataToList()
        adapter = RestaurantAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                    return true
            }
        })
    }

    private fun filterList(query : String?){
        if(query != null){
            val filteredList = ArrayList<Restaurants>()
            for(i in mList){
                if(i.title.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(requireContext(),"No Data found",Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList(){
        mList.add(Restaurants("Burger King",R.drawable.burgerkinglogo))
        mList.add(Restaurants("McDonald's",R.drawable.mcdonaldslogo))
        mList.add(Restaurants("Ohannes",R.drawable.ohanneslogo))
        mList.add(Restaurants("Subway",R.drawable.subway))
        mList.add(Restaurants("Sushico",R.drawable.sushicologo))
        mList.add(Restaurants("Baydöner",R.drawable.baydonerlogo))
        mList.add(Restaurants("Domino's",R.drawable.dominoslogo))
    }
}