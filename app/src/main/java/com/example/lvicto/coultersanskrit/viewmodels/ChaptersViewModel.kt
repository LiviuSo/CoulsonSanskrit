package com.example.lvicto.coultersanskrit.viewmodels


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.lvicto.coultersanskrit.repositories.ChaptersRepository

class ChaptersViewModel : ViewModel() {

    private val repository: ChaptersRepository = ChaptersRepository()

    internal val chapterTitles: LiveData<ArrayList<String>>

    init {
        chapterTitles = repository.chapters
    }
}