package com.example.restaurant

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SubDetailedActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var restList : ArrayList<DoubleSubNames>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_detailed)

        recyclerView = findViewById(R.id.sub_detailed)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)

        restList = ArrayList()

    }
}