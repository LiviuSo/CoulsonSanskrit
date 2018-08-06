package com.example.lvicto.coultersanskrit.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.lvicto.coultersanskrit.db.entity.Word

@Dao
 interface WordDao {

    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

   // todo add update word

    // todo add delete word
}