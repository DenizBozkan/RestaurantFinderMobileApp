package com.example.restaurant.fragments

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.*

class HomeFragment : Fragment(R.layout.first_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var subRecyclerView: RecyclerView
    private lateinit var nameList : ArrayList<Names>
    private lateinit var subNameList : ArrayList<SubNames>
    private lateinit var nameAdapter: NameAdapter
    private lateinit var subNameAdapter: SubNameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return  inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        recyclerView = view.findViewById(R.id.rv_main_category)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        nameList = ArrayList()

        nameList.add(Names(R.drawable.burgericon, "Burger Restaurants"))
        nameList.add(Names(R.drawable.kebapicon, "Kebap Restaurants"))
        nameList.add(Names(R.drawable.pizzaicon, "Pizza Restaurants"))
        nameList.add(Names(R.drawable.sandwichicon, "Sandwich Restaurants"))
        nameList.add(Names(R.drawable.sushiicon, "Sushi Restaurants"))

        nameAdapter = NameAdapter(nameList)
        recyclerView.adapter = nameAdapter


        subRecyclerView = view.findViewById(R.id.rv_sub_category)
        subRecyclerView.setHasFixedSize(true)
        subRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        subNameList = ArrayList()

        subNameList.add(SubNames(R.drawable.sushico, "Sushico"))
        subNameList.add(SubNames(R.drawable.dominos, "Domino's"))
        subNameList.add(SubNames(R.drawable.burgerking, "Burger King"))
        subNameList.add(SubNames(R.drawable.subway, "Subway"))
        subNameList.add(SubNames(R.drawable.baydoner, "Baydöner"))

        subNameAdapter = SubNameAdapter(subNameList)
        subRecyclerView.adapter = subNameAdapter

        subNameAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailedActivity::class.java)
            intent.putExtra("name", it)
            startActivity(intent)
        }
    }
}
