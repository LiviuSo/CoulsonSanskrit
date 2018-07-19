package com.example.lvicto.coultersanskrit.repositories

import android.arch.lifecycle.MutableLiveData
import com.example.lvicto.coultersanskrit.models.TitlesProvider

class ChaptersRepository {

    val chapters: MutableLiveData<ArrayList<String>> = MutableLiveData()

    init {
        chapters.value = TitlesProvider.generateChapterTitles()
    }
}
