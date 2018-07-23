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
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.SANSKRIT_KEYS_NAME_1
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_1
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper.isDefaultInputMethod
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper.sendToPlayStore
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper.showSoftInputMethods
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper.isSoftInputEnabled
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper.softInputInstalled
import com.example.lvicto.coultersanskrit.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewModel: ChaptersViewModel // todo: expose it with setter to testing


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init RecyclerView
        recyclerView = findViewById(R.id.chapters)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TitlesAdapter(this)

        // init viewmodel
        viewModel = ViewModelProviders.of(this).get(ChaptersViewModel::class.java)
        viewModel.chapterTitles.observe(this, Observer<ArrayList<String>> {
            titles -> (recyclerView.adapter as TitlesAdapter).data = titles!!
        })

        // install/choose soft input
        if(!softInputInstalled(SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_1)) {
            sendToPlayStore() // not necessary (we'll have our own keyboard)
            PreferenceHelper.setKeyboardInstalled(true) // show input chooser later
        } else if( !isSoftInputEnabled(SANSKRIT_KEYS_NAME_1) || !isDefaultInputMethod(SANSKRIT_KEYS_NAME_1)) {
            showSoftInputMethods()
            if(!isDefaultInputMethod(SANSKRIT_KEYS_NAME_1)) {
                PreferenceHelper.setKeyboardInstalled(true) // show input chooser later
            }
        }
    }
}