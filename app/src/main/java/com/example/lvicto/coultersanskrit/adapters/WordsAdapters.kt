package com.example.lvicto.coultersanskrit.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.db.entity.Word
import java.util.ArrayList


class WordsAdapter(val context: Context) : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    var words: List<Word>? = null
        set(value) {
            if(value != null) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsAdapter.WordViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
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
            holder.bindData(words!![position])
        } else {
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
        }
    }

    class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(word: Word) { // todo complete
            view.findViewById<TextView>(R.id.tv_item_word).text = word.word
        }
    }
}
