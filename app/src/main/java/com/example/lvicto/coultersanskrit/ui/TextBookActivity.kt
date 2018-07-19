package com.example.lvicto.coultersanskrit.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.example.lvicto.coultersanskrit.R


class TextBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_book)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.title = "${getString(R.string.chapter)} xxx"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val imageTextBook = findViewById<ImageView>(R.id.textBookCut)
        imageTextBook.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ch03_nominal_senteces))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}