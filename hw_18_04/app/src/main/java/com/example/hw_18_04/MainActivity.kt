package com.example.hw_18_04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_18_04.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var wordList: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etWord.inputType = android.text.InputType.TYPE_CLASS_TEXT

        loadWordsFromAssets()

        binding.btnSearch.setOnClickListener {
            val input = binding.etWord.text.toString().trim().lowercase()

            if (input.isEmpty()) {
                binding.tvResult.text = "Введите слово!"
                return@setOnClickListener
            }

            if (wordList.isEmpty()) {
                binding.tvResult.text = "Словарь не загружен"
                return@setOnClickListener
            }

            val rhymes = findRhymes(input)

            if (rhymes.isEmpty()) {
                binding.tvResult.text = "Рифм не найдено для слова: $input"
            } else {
                binding.tvResult.text = "Рифмы к слову «$input» (${rhymes.size} шт.):\n\n" +
                        rhymes.joinToString("\n")
            }
        }
    }

    private fun loadWordsFromAssets() {
        try {
            val inputStream = assets.open("ua_words.txt")
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

            wordList = reader.readLines()
                .map { it.trim().lowercase() }
                .filter { it.isNotEmpty() && it.length > 2 }

            reader.close()

            binding.tvResult.text = "Словарь загружен (${wordList.size} слов). Введите слово."
        } catch (e: Exception) {
            binding.tvResult.text = "Ошибка загрузки файла:\n${e.message}"
        }
    }

    private fun findRhymes(word: String): List<String> {
        if (word.length < 2) return emptyList()

        val ending2 = word.takeLast(2)
        val ending3 = if (word.length >= 3) word.takeLast(3) else ""

        return wordList.filter { w ->
            w != word && (
                    (ending3.isNotEmpty() && w.endsWith(ending3)) ||
                            w.endsWith(ending2)
                    )
        }.sorted()
    }
}