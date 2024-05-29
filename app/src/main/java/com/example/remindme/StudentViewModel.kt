package com.example.remindme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(
    private val studentRepository: StudentRepository
):ViewModel() {

    val allStudents:LiveData<List<StudentEntity>> = studentRepository.allStudents

    fun insert(student: StudentEntity) = viewModelScope.launch {
        studentRepository.insert(student)
    }

    fun update(student: StudentEntity) = viewModelScope.launch {
        studentRepository.update(student)
    }

    fun delete(student: StudentEntity) = viewModelScope.launch {
        studentRepository.delete(student)
    }
}