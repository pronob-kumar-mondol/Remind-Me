package com.example.remindme

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentEntity::class], version = 1,exportSchema = false)
abstract class StudentDatabase:RoomDatabase() {

    abstract fun studentDao():StudentDao

    companion object{
        @Volatile
        private var INSTANCE:StudentDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: getDatabase(context).also { INSTANCE = it }
        }

        private fun getDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            StudentDatabase::class.java,
            "student_database.db"
        ).build()
    }


}