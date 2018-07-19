package com.example.lvicto.coultersanskrit.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.arch.lifecycle.ViewModelProviders
import com.example.lvicto.coultersanskrit.viewmodels.ChaptersViewModel
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.adapters.TitlesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var titlesAdapter: TitlesAdapter

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewModel: ChaptersViewModel // todo: expose it with setter to testing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.chapters)
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProviders.of(this).get(ChaptersViewModel::class.java)
        viewModel.chapterTitles.observe(this, Observer<ArrayList<String>> {
            titles -> recyclerView.adapter = TitlesAdapter(this@MainActivity, titles!!)
        })
    }
}