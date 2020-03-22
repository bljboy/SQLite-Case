package com.example.sqlitecase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    private val fruitList: List<ItemName> = ArrayList<ItemName>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mutableMapOf = mutableMapOf<Int, String>()
        mutableMapOf.put(1, "ss")
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = MyAdapter(this, mutableMapOf);
        recyclerview.setHasFixedSize(true)



        bt_add.setOnClickListener(View.OnClickListener {


        })





    }


}
