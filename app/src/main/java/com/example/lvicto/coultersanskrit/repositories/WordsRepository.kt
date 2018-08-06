package com.example.lvicto.coultersanskrit.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.lvicto.coultersanskrit.db.WordsDatabase
import com.example.lvicto.coultersanskrit.db.dao.WordDao
import com.example.lvicto.coultersanskrit.db.entity.Word

class WordsRepository  internal constructor(val application: Application) {

    private val wordsDao: WordDao = WordsDatabase.getInstance(application)!!.wordDao()
    val allWords: LiveData<List<Word>>

    init {
        allWords = wordsDao.getAllWords()
    }

    fun insertWord(word: Word) {
        InsertAsyncTask(mAsyncTaskDao = wordsDao).execute(word)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}