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
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.adapters.WordsAdapter
import com.example.lvicto.coultersanskrit.db.WordsDatabase
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.utils.Constants
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.NEW_WORD_ACTIVITY_REQUEST_CODE
import com.example.lvicto.coultersanskrit.viewmodels.WordsViewModel
import com.google.gson.Gson
import com.example.lvicto.coultersanskrit.data.Words

class AllWordsActivity : AppCompatActivity() {

    private lateinit var viewModel: WordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_words)

        // for testing
        val db = WordsDatabase.getInstance(this)!!
        db.popupateDbForTesting()

        initUI()
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

    private fun initUI() {
        viewModel = ViewModelProviders.of(this).get(WordsViewModel::class.java)

        val btnLoad = findViewById<Button>(R.id.btnLoad)
        btnLoad.setOnClickListener { _ ->
            viewModel.loadFromPrivateFile().observe(AllWordsActivity@this, loadFromJsomObserver)
        }
        btnLoad.visibility = View.GONE

        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener { _ ->
            viewModel.allWords.observe(this, saveToFileObserver)
        }
        btnSave.visibility = View.GONE

        val llImport = findViewById<LinearLayout>(R.id.llJsonImport)
        findViewById<Button>(R.id.btnImport).setOnClickListener { _ ->
            llImport.visibility = View.VISIBLE
            viewModel.loadFromPrivateFile().observe(AllWordsActivity@this, importObserver)
        }
        findViewById<Button>(R.id.btnExport).setOnClickListener { _ ->
            viewModel.allWords.observe(this, exportObserver)
        }
        val edit = findViewById<EditText>(R.id.editJson)
        findViewById<Button>(R.id.btnLoadJson).setOnClickListener {
//            Toast.makeText(AllWordsActivity@this, "Load Json", Toast.LENGTH_SHORT).show()
            viewModel.loadFromString(edit.text.toString()).observe(AllWordsActivity@this, loadFromJsomObserver)
            llImport.visibility = View.GONE
        }

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

    companion object {
        private val LOG_TAG = AllWordsActivity::class.java.simpleName
    }

    private val loadFromJsomObserver = Observer<String> { it ->
        Log.d(LOG_TAG, it)
        if(!it!!.isEmpty()) {
            val list = Gson().fromJson(it, Words::class.java)
            list.list.forEach {
                Log.d(LOG_TAG, it.toString())
                viewModel.insert(it)
            }
        }
    }

    private val saveToFileObserver = Observer<List<Word>> { it ->
        if (it != null) {
            // save on private file
            viewModel.saveToPrivateFile(Words(it)).observe(AllWordsActivity@this, Observer<()->Unit> {
                it?.invoke() // todo make it return json string
                // todo launch share intent
            })
        }
    }

    private val exportObserver = Observer<List<Word>> {
        val jsonString = Gson().toJson(Words(it!!))
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sanskrit dic as json (String)")
        intent.putExtra(Intent.EXTRA_TEXT, jsonString)
        startActivity(Intent.createChooser(intent, "Share using")) // todo make a string resource
    }

    private val importObserver = Observer<String> {
        // todo
    }
}
