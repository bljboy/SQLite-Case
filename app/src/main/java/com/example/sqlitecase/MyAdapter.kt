package com.example.sqlitecase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_text.view.*

class MyAdapter(
    mainActivity: MainActivity,
    map: MutableMap<Int, String>
) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var datas = map
    var activity = mainActivity
    var alist = ArrayList<Int>()
    var blist = ArrayList<String>()

    init {
        for (data in datas.keys) {
            alist.add(data)
            blist.add(datas.get(data)!!)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        val holder = MyHolder(itemView)
        return holder

    }


    override fun getItemCount(): Int {
        return datas.count()
    }

    override fun onBindViewHolder(holder: MyAdapter.MyHolder, position: Int) {
        holder.id.setText(alist.get(position).toString())
        holder.name.setText(blist.get(position).toString())
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView
        val id: TextView

        init {
            name = itemView.tv_name
            id = itemView.tv_id
        }
    }

}
