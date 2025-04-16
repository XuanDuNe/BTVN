package com.example.thuchanh1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.thuchanh1.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            addEvents()
        }

    private fun addEvents() {
        xulyCheck()
    }

    private fun xulyCheck() {
        binding.btnCheck.setOnClickListener{
            var ten = binding.edtName.text.toString()
            var age = binding.edtAge.text.toString().toInt()
            //kiem tra co nhap tuoi khong
            if (binding.edtAge.text.toString().isEmpty()){
                Toast.makeText(this, "Vui long nhap tuoi", Toast.LENGTH_SHORT).show()
                
            }
            if(age == null){
                Toast.makeText(this, "Tuoi phai la so", Toast.LENGTH_SHORT).show()
            }
            var category = when{
                age> 65 -> "Người già"
                age in 6..65 -> "Người lớn"
                age in 2..6 -> "Trẻ em"
                age > 0 -> "Em bé"
                else -> "Tuổi không hợp lệ"
            }
            binding.edtHienThi.setText("$ten thuộc nhóm: $category")
            binding.edtAge.setText("")
            binding.edtName.setText("")
        }
    }
}
