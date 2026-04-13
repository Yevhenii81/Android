package com.example.hw_04_04

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_04_04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowDialog.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_hw_04_04, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView.findViewById<android.widget.Button>(R.id.btn1).setOnClickListener { dialog.dismiss() }
        dialogView.findViewById<android.widget.Button>(R.id.btn2).setOnClickListener { dialog.dismiss() }
        dialogView.findViewById<android.widget.Button>(R.id.btn3).setOnClickListener { dialog.dismiss() }
        dialogView.findViewById<android.widget.Button>(R.id.btn4).setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}