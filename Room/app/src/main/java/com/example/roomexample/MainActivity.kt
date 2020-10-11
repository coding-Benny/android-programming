package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.recyclerview_item.view.*

import kotlinx.coroutines.runBlocking
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myDAO: MyDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter(this)
        binding.studentList.adapter = adapter
        binding.studentList.layoutManager = LinearLayoutManager(this)

        myDAO = MyDatabase.getDatabase(this).getMyDao()
        runBlocking {
            with(myDAO) {
                insertStudent(Student(1, "james"))
                insertStudent(Student(2, "john"))
                insertClass(ClassInfo(1, "c-lang", "Mon 9:00", "E301", 1))
                insertClass(ClassInfo(2, "android prog", "Mon 9:00", "E302", 1))
                insertClass(ClassInfo(3, "network prog", "Wed 9:00", "E303", 2))
                insertEnrollment(Enrollment(1, 1))
                insertEnrollment(Enrollment(1, 2))
            }
        }

        val allStudents = myDAO.getAllStudents()
        allStudents.observe(this, { students ->
           students?.let { adapter.setStudents(it) }
        })

        binding.studentList.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = rv.findChildViewUnder(e.x, e.y)
                val position = rv.getChildAdapterPosition(child!!)
                val studentInfo = binding.studentList[position].textView.text
                val studentInfoArray = studentInfo.split("-").map { it.trim() }
                val id = studentInfoArray[0]
                binding.editStudentId.setText(id)
                binding.queryStudent.performClick()
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        binding.queryStudent.setOnClickListener {
            val id = binding.editStudentId.text.toString().toInt()
            runBlocking {
                val results = myDAO.getStudentsWithEnrollment(id)
                if (results.isNotEmpty()) {
                    val str = StringBuilder().apply {
                        append(results[0].student.id)
                        append("-")
                        append(results[0].student.name)
                        append(":")
                        for (c in results[0].enrollments) {
                            append(c.cid)
                            val cls_result = myDAO.getClassInfo(c.cid)
                            if (cls_result.isNotEmpty())
                                append("(${cls_result[0].name})")
                            append(",")
                        }
                    }
                    binding.textQueryStudent.text = str
                } else {
                    binding.textQueryStudent.text = ""
                }
            }
        }

        binding.addStudent.setOnClickListener {
            val id = binding.editStudentId.text.toString().toInt()
            val name = binding.editStudentName.text.toString()
            if (id > 0 && name.isNotEmpty()) {
                runBlocking {
                    myDAO.insertStudent(Student(id, name))
                }
            }
        }

        binding.enrollStudent.setOnClickListener {
            val id = Integer.parseInt(binding.editStudentId.text.toString())
            runBlocking {
                with(myDAO) {
                    val name = getStudentById(id)[0].name
                    Log.d("TAG", "$id, $name")
                    insertEnrollment(Enrollment(id, 3))
                }
            }
        }

        binding.deleteStudent.setOnClickListener {
            val id = Integer.parseInt(binding.editStudentId.text.toString())
            runBlocking {
                with(myDAO) {
                    val name = getStudentById(id)[0].name
                    deleteStudent(Student(id, name))
                }
            }
        }
    }
}