package com.example.lvicto.coultersanskrit.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.db.entity.Word


class WordsAdapter(val context: Context,
                   private val clickListener: View.OnClickListener,
                   private val longClickListener: View.OnLongClickListener) : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    var words: List<Word>? = null
        set(value) {
            if(value != null) {
                field = value
                notifyDataSetChanged()
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsAdapter.WordViewHolder {
        val view = if(type == TYPE_NON_REMOVABLE) {
            LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)
        } else {
            LayoutInflater.from(context).inflate(R.layout.item_word_removable, parent, false)
        }
        return WordViewHolder(view, clickListener, longClickListener)
    }

    override fun getItemCount(): Int {
        return if(words != null) {
            words!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: WordsAdapter.WordViewHolder, position: Int) {
        if(words != null) {
            holder.bindData(words!![position], getItemViewType(position))
        } else {
            Log.e(LOG_TAG, "Empty or null data")
        }
    }

    var type: Int = TYPE_NON_REMOVABLE

    override fun getItemViewType(position: Int): Int = type

    class WordViewHolder(val view: View,
                         private val clickListener: View.OnClickListener,
                         private val longClickListener: View.OnLongClickListener) : RecyclerView.ViewHolder(view) {

        fun bindData(word: Word, type: Int) { // todo complete
            view.tag = word
            view.findViewById<TextView>(R.id.tvItemWord).text = word.word
            view.setOnClickListener(clickListener)
            view.setOnLongClickListener(longClickListener)
            when (type) {
                TYPE_NON_REMOVABLE -> {
                    // do specifics
                }
                TYPE_REMOVABLE -> {
                    // do specifics
                }
                else -> {
                    Log.d(LOG_TAG, "Unknown type of recycler view item ")
                }
            }
        }
    }

    companion object {
        const val TYPE_REMOVABLE = 1
        const val TYPE_NON_REMOVABLE = 2
        val LOG_TAG = WordsAdapter::class.java.toString()
    }
}