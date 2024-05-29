    package com.example.remindme

    import android.app.Application
    import androidx.lifecycle.AndroidViewModel
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.launch

    class StudentViewModel(application: Application) : AndroidViewModel(application) {

        private val repository: StudentRepository
        val allStudents: LiveData<List<StudentEntity>>

        init {
            val studentDao = StudentDatabase.getDatabase(application).studentDao()
            repository = StudentRepository(studentDao)
            allStudents = repository.allStudents
        }

        fun insert(student: StudentEntity) = viewModelScope.launch {
            repository.insert(student)
        }

        fun update(student: StudentEntity) = viewModelScope.launch {
            repository.update(student)
        }

        fun delete(student: StudentEntity) = viewModelScope.launch {
            repository.delete(student)
        }
    }