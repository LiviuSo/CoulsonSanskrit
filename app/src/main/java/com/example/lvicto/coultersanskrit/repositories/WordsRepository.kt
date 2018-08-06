package com.example.lvicto.coultersanskrit.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.lvicto.coultersanskrit.db.WordsDatabase
import com.example.lvicto.coultersanskrit.db.dao.WordDao
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.utils.DbWorkerThread

class WordsRepository  internal constructor(val application: Application) {

    private val wordsDao: WordDao = WordsDatabase.getInstance(application)!!.wordDao()
    private var worker: DbWorkerThread = DbWorkerThread("bd_worker_thread")
    val allWords: LiveData<List<Word>>

    init {
        allWords = wordsDao.getAllWords()
    }

    fun insertWord(word: Word) {
        val task = Runnable { WordsDatabase.getInstance(application)!!.wordDao().insert(word) }
        worker.postTask(task)
    }
}