package com.example.makemynotes.database


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String,
    var title: String,
    var description: String,
    val color: Int
) : Parcelable

