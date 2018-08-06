package com.example.lvicto.coultersanskrit.ui.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.adapters.WordsAdapter
import com.example.lvicto.coultersanskrit.db.WordsDatabase
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.utils.Constants
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.NEW_WORD_ACTIVITY_REQUEST_CODE
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

        val fab = findViewById<FloatingActionButton>(R.id.fabDictionary)
        fab.setOnClickListener {
            val intent = Intent(MainActivity@this, AddWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val wordRo = data!!.getStringExtra(Constants.Keyboard.REPLY_ADD_WORD_WORD_RO)
                val wordEn = data.getStringExtra(Constants.Keyboard.REPLY_ADD_WORD_WORD_EN)
                val wordSa = data.getStringExtra(Constants.Keyboard.REPLY_ADD_WORD_WORD_SA)
                val word = Word(word = wordSa, meaningEn = wordEn, meaningRo = wordRo)
                viewModel.insert(word)
            } else {
                Toast.makeText(this, "Word not saved because of missing fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
