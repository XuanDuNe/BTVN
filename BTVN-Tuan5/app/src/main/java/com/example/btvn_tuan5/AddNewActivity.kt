package com.example.btvn_tuan5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        val titleEditText = findViewById<TextInputEditText>(R.id.titleEditText)
        val descriptionEditText = findViewById<TextInputEditText>(R.id.descriptionEditText)
        val saveButton = findViewById<MaterialButton>(R.id.saveButton)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                // TODO: Save the data
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
} 