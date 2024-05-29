package com.example.remindme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "student_name")
    val name: String,
    @ColumnInfo(name = "student_number")
    val number: String,
    @ColumnInfo(name = "student_details")
    val details: String,
    @ColumnInfo(name = "student_date")
    val date: String,
    @ColumnInfo(name = "student_time")
    val time: String
)

