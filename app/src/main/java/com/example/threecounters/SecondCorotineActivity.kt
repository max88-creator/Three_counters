package com.example.threecounters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondCorotineActivity : AppCompatActivity() {
    private lateinit var goButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_corotine)
        goButton = findViewById(R.id.goBtn)
        goButton.setOnClickListener {
            val intent = Intent(this, ThirdCoroutineActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            val x = load()
            Log.d("Key", x)
        }
    }
    suspend fun load(): String {
        delay(3000)
        return "Данные загружены!"
    }
}