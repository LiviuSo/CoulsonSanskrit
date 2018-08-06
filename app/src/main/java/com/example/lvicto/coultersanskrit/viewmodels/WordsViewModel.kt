package com.example.lvicto.coultersanskrit.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.repositories.WordsRepository

class WordsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: WordsRepository = WordsRepository(application = application)
    val allWords: LiveData<List<Word>>

    init {
        allWords = repo.allWords
    }

    fun insert(word: Word) {
        repo.insertWord(word)
    }
}