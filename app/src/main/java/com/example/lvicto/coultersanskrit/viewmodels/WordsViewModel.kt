package com.example.lvicto.coultersanskrit.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.lvicto.coultersanskrit.db.entity.Word
import com.example.lvicto.coultersanskrit.repositories.FileRepository
import com.example.lvicto.coultersanskrit.repositories.WordsRepository
import com.google.gson.Gson
import com.example.lvicto.coultersanskrit.data.Words

class WordsViewModel(val app: Application) : AndroidViewModel(app) {

    private val repo: WordsRepository = WordsRepository(application = app)
    val allWords: LiveData<List<Word>>

    init {
        allWords = repo.allWords
    }

    fun insert(word: Word) {
        repo.insertWord(word)
    }

    // todo use when implement FileProvider
    fun loadFromPrivateFile() : LiveData<String> {
        val ld = FileRepository.loadFromPrivateFile(app.applicationContext) // todo use RxJava
        return ld
    }

    // todo use when implement FileProvider
    fun saveToPrivateFile(words: Words) : LiveData<()->Unit> {
        return FileRepository.saveToPrivateFile(context = app.applicationContext, json = Gson().toJson(words))
    }

    fun loadFromString(json: String): LiveData<String> {
        val mutableLiveData: MutableLiveData<String> = MutableLiveData()
        mutableLiveData.value = json
        return mutableLiveData
    }


}