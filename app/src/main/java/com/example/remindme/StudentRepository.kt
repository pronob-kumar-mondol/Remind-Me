package com.example.remindme

class StudentRepository(
    private val studentDao: StudentDao
) {

    val allStudents = studentDao.getAllStudents()

    suspend fun insert(student: StudentEntity) = studentDao.insert(student)

    suspend fun update(student: StudentEntity) = studentDao.update(student)

    suspend fun delete(student: StudentEntity) = studentDao.delete(student)

    suspend fun deleteById(id: Int) = studentDao.deleteById(id)

    suspend fun deleteAll() = studentDao.deleteAll()
}