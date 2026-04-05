package com.example.hw_03_04;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val chats = listOf(
            Chat("Jornal da Cidade Online", "https://www.jornaldacid...", "13:30", 1380),
            Chat("Hindustan Times", "India saw another big ju...", "08:50", 16),
            Chat("Financial Times", "Schools must always t...", "Wed", 19),
            Chat("Saved Messages", "Draft: Monospaced", "Wed", 97),
            Chat("Bitcoin Channel", "Happy Eid Mubarak ", "Fri", 32),
            Chat("xAI Grok", "Some interesting update...", "10:35", 5),
            Chat("News Ukraine", "Останні новини сьогодні", "Yesterday", 0)
        )

        val adapter = ChatAdapter(chats)
        recyclerView.adapter = adapter
    }
}