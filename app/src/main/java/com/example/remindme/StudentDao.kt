package com.example.remindme

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface StudentDao {

    @Query("SELECT * FROM student order by id ASC")
    fun getAllStudents(): LiveData<List<StudentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: StudentEntity)

    @Update
    suspend fun update(student: StudentEntity)

    @Delete
    suspend fun delete(student: StudentEntity)

    @Query("DELETE FROM student WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

}