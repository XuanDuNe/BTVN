package com.example.hethongquanlythuvien

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.hethongquanlythuvien.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var ds: MutableList<String> = mutableListOf("Sách 1", "Sách 2") // Danh sách sách

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("LibraryPrefs", Context.MODE_PRIVATE)

        // Lấy tên nhân viên đã lưu trước đó (nếu có)
        val savedName = sharedPreferences.getString("employee_name", "")
        binding.edtNameEmployee.setText(savedName)

        // Khởi tạo Adapter cho ListView
        binding.lvBook.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ds
        )

        addEvents()
    }

    private fun addEvents() {
        binding.btnAddBook.setOnClickListener {
            addBook()
        }

        binding.btnNameEmployee.setOnClickListener {
            addNameEmployee()
        }
    }

    private fun addBook() {
        val book: String = binding.edtNameBook.text.toString()
        if (book.isNotEmpty()) { // Kiểm tra nếu ô nhập không rỗng
            ds.add(book) // Thêm sách vào danh sách
            binding.edtNameBook.setText("")
            binding.edtNameBook.requestFocus()
            (binding.lvBook.adapter as ArrayAdapter<String>).notifyDataSetChanged() // Cập nhật ListView
        }
    }

    private fun addNameEmployee() {
        val newName = binding.edtNameEmployee.text.toString()
        if (newName.isNotEmpty()) {
            // Lưu tên nhân viên vào SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("employee_name", newName)
            editor.apply()
        }
    }
}
