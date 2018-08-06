package com.example.lvicto.coultersanskrit.ui.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.REPLY_ADD_WORD_WORD_EN
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.REPLY_ADD_WORD_WORD_RO
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.REPLY_ADD_WORD_WORD_SA

class AddWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        val editWord = findViewById<EditText>(R.id.editSa)
        val editWordRo = findViewById<EditText>(R.id.editRo)
        val editWordEn = findViewById<EditText>(R.id.editEn)
        val buttonSave = findViewById<Button>(R.id.btnAddWord)
        buttonSave.setOnClickListener {
            val word = editWord.text.toString()
            val wordEn = editWordEn.text.toString()
            val wordRo = editWordRo.text.toString()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(word) ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(REPLY_ADD_WORD_WORD_SA, word)
                replyIntent.putExtra(REPLY_ADD_WORD_WORD_EN, wordEn)
                replyIntent.putExtra(REPLY_ADD_WORD_WORD_RO, wordRo)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
