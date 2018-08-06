package com.example.lvicto.coultersanskrit.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.adapters.WordsAdapter
import com.example.lvicto.coultersanskrit.db.WordsDatabase
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.viewmodels.WordsViewModel

class AllWordsActivity : AppCompatActivity() {

    lateinit var viewModel: WordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_words)

        // for testing
        val db = WordsDatabase.getInstance(this)!!
        db.popupateDbForTesting()

        viewModel = ViewModelProviders.of(this).get(WordsViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_words)
        val wordsAdapter = WordsAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = wordsAdapter

        viewModel.allWords.observe(this, Observer<List<Word>> {
            wordsAdapter.words = it
        })
    }
}
