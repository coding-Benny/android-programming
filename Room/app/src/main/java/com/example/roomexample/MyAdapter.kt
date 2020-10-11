package com.example.roomexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter internal constructor(context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var students = emptyList<Student>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentItemView: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = students[position]
        holder.studentItemView.text = "${current.id}-${current.name}\n"
    }

    internal fun setStudents(student: List<Student>) {
        this.students = student
        notifyDataSetChanged()
    }

    override fun getItemCount() = students.size
}