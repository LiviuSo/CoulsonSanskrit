package com.example.lvicto.coultersanskrit.ui.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.EXTRA_WORD
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.EXTRA_WORD_ID
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.EXTRA_WORD_WORD_EN
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.EXTRA_WORD_RO
import com.example.lvicto.coultersanskrit.utils.Constants.Keyboard.EXTRA_WORD_SA

class AddModifyWordActivity : AppCompatActivity() {

    private lateinit var editWord: EditText
    private lateinit var editWordRo: EditText
    private lateinit var editWordEn: EditText
    private lateinit var buttonSave: Button
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        editWord = findViewById(R.id.editSa)
        editWordRo = findViewById(R.id.editRo)
        editWordEn = findViewById(R.id.editEn)
        buttonSave = findViewById(R.id.btnSaveWord)

        if (intent.hasExtra(EXTRA_WORD)) {
            val word: Word = intent.getParcelableExtra("EXTRA_WORD")
            id = word.id
            editWord.setText(word.word)
            editWordRo.setText(word.meaningRo)
            editWordEn.setText(word.meaningEn)
        }
        buttonSave.setOnClickListener(this::onClickAdd)
    }

    private fun onClickAdd(v: View) {
        val word = editWord.text.toString()
        val wordEn = editWordEn.text.toString()
        val wordRo = editWordRo.text.toString()

        val replyIntent = Intent()
        if (TextUtils.isEmpty(word)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            if(id != null) {
                replyIntent.putExtra(EXTRA_WORD_ID, id!!)
            }
            replyIntent.putExtra(EXTRA_WORD_SA, word)
            replyIntent.putExtra(EXTRA_WORD_WORD_EN, wordEn)
            replyIntent.putExtra(EXTRA_WORD_RO, wordRo)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }
}
