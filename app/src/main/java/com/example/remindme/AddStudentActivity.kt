package com.example.remindme

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class AddStudentActivity : AppCompatActivity() {

    private lateinit var editTextStudentName: EditText
    private lateinit var editTextStudentNumber: EditText
    private lateinit var editTextDetails: EditText
    private lateinit var textViewDate: TextView
    private lateinit var textViewTime: TextView
    private lateinit var buttonDate: Button
    private lateinit var buttonTime: Button
    private lateinit var buttonSave: Button

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(application)
    }

    private var appointmentDate: String = ""
    private var appointmentTime: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        editTextStudentName = findViewById(R.id.editTextStudentName)
        editTextStudentNumber = findViewById(R.id.editTextStudentNumber)
        editTextDetails = findViewById(R.id.editTextDetails)
        textViewDate = findViewById(R.id.textViewDate)
        textViewTime = findViewById(R.id.textViewTime)
        buttonDate = findViewById(R.id.buttonDate)
        buttonTime = findViewById(R.id.buttonTime)
        buttonSave = findViewById(R.id.buttonSave)

        buttonDate.setOnClickListener { showDatePicker() }
        buttonTime.setOnClickListener { showTimePicker() }
        buttonSave.setOnClickListener { saveStudent() }

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                appointmentDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                textViewDate.text = appointmentDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                appointmentTime = "$selectedHour:$selectedMinute"
                textViewTime.text = appointmentTime
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }

    private fun saveStudent() {
        val name = editTextStudentName.text.toString().trim()
        val number = editTextStudentNumber.text.toString().trim()
        val details = editTextDetails.text.toString().trim()

        if (name.isNotEmpty() && number.isNotEmpty() && details.isNotEmpty() && appointmentDate.isNotEmpty() && appointmentTime.isNotEmpty()) {
            val student = StudentEntity(name = name, number = number, details = details, date = appointmentDate, time = appointmentTime)
            studentViewModel.insert(student)
            finish() // Go back to the previous activity
        }
    }
}