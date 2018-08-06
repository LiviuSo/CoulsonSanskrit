package com.example.lvicto.coultersanskrit.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(@field:PrimaryKey(autoGenerate = true) @field:ColumnInfo(name = "id") val id: Int = 0,
                @field:ColumnInfo(name = "word") val word: String,
                @field:ColumnInfo(name = "meaningEn") val meaningEn: String = "",
                @field:ColumnInfo(name = "meaningRo") val meaningRo: String = "")