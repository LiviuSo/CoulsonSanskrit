package com.example.lvicto.coultersanskrit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayList

class TitlesAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<String> = TitlesHelper.generateChapterTitles()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.chapter_title, parent, false)
        return ChapterTitleView(item)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChapterTitleView).bindData(data[position],
                getItemViewType(position),
                View.OnClickListener {
                    Toast.makeText(context, "Tapped: ${data[position]}", Toast.LENGTH_SHORT).show()
                    val helper = TitlesHelper(data)
                    // collapse if already expanded or just expand
                    if (helper.isExpanded(position)) {
                        helper.collapseData(position)
                    } else {
                        helper.expandData(position)
                    }
                    notifyDataSetChanged()
                    Log.d(LOG_TAG, "new data: $helper")
                },
                View.OnClickListener {
                    Toast.makeText(context, "Tapped section", Toast.LENGTH_SHORT).show()
                }
        )
    }

    override fun getItemViewType(position: Int): Int =
            if (data[position].toLowerCase().contains("Chapter", true))
                TYPE_CHAPTER
            else
                TYPE_SECTION

    companion object {
        private val LOG_TAG = TitlesAdapter::class.java.simpleName
        private const val TYPE_CHAPTER = 0
        private const val TYPE_SECTION = 1

    }

    class ChapterTitleView(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bindData(data: String, type: Int, listenerChapter: View.OnClickListener, listenerSection: View.OnClickListener) {
            item.apply {
                findViewById<TextView>(R.id.name).apply {
                    text = data
                    if(type == TYPE_CHAPTER) {
                        setOnClickListener(listenerChapter)
                    } else if(type == TYPE_SECTION) {
                        setOnClickListener(listenerSection)
                    }
                }
            }
        }
    }
}
