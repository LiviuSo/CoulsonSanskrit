package com.example.lvicto.coultersanskrit

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.chapters)
        val chapterTitles = arrayListOf("Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5")
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HeaderAdapter(this, data = chapterTitles)


    }

    class HeaderAdapter(private val context : Context, val data: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val item = LayoutInflater.from(parent.context).inflate(R.layout.chapter_title, parent, false)
            return ChapterTitleView(item)
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as ChapterTitleView).bindData(data[position], View.OnClickListener {
                Toast.makeText(context, "Tapped: ${data[position]}", Toast.LENGTH_SHORT).show()
                val helper = HeadersTitlesHelper(data)
                // collapse if already expanded or just expand
                if(helper.isExpanded(position)) {
                    helper.collapseData(position)
                } else {
                    helper.expandData(position)
                }
                notifyDataSetChanged()
                Log.d(LOG_TAG, "new data: $helper")
            })
        }
    }

    class ChapterTitleView(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bindData(data: String, listener: View.OnClickListener) {
            item.apply {
                findViewById<TextView>(R.id.name).apply {
                    text = data
                    setOnClickListener(listener)
                }
            }
        }
    }
}

