package com.talo.recycleviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talo.recycleviewsample.databinding.ActivityMainBinding


    val theList = mutableListOf<String>().apply {
        for (i in 0..20) {
            add("Item + $i")
        }
    }

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = Adapter()
            setHasFixedSize(true)
        }

    }

    class ListHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView = view.findViewById<ImageView>(R.id.img)
        var textView = view.findViewById<TextView>(R.id.txt)
        var button = view.findViewById<Button>(R.id.btn_press)

    }

    class Adapter : RecyclerView.Adapter<ListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
            return ListHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ListHolder, position: Int) {
            holder.textView.text = theList[position]
            holder.button.setOnClickListener {
                Toast.makeText(it.context, "${holder.textView.text}", Toast.LENGTH_SHORT).show()
            }
            holder.itemView.setOnClickListener {
                Toast.makeText(it.context, "Is $position", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int {
            return theList.size
        }

    }
}