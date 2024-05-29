package com.example.remindme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter:RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students = emptyList<StudentEntity>()

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById(R.id.textView)
        val studentNumber: TextView = itemView.findViewById(R.id.textView2)
        val studentDetails: TextView = itemView.findViewById(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = students[position]
        holder.studentName.text = currentStudent.name
        holder.studentNumber.text = currentStudent.number
        holder.studentDetails.text = currentStudent.details
    }

    override fun getItemCount() = students.size

    fun setStudents(newStudents: List<StudentEntity>) {
        val diffCallback = StudentDiffCallback(students, newStudents)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        students = newStudents
        diffResult.dispatchUpdatesTo(this)
    }

    class StudentDiffCallback(
        private val oldList: List<StudentEntity>,
        private val newList: List<StudentEntity>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}