package com.example.threecounters

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    private lateinit var firstCounterText: TextView
    private lateinit var secondCounterText: TextView
    private lateinit var thirdCounterText: TextView
    private lateinit var launchButton: Button
    private lateinit var stopButton: Button
    private var isRunning = false
    private var counter1 = 0
    private var counter2 = 0
    private var counter3 = BigInteger.valueOf(2)
    private val job = SupervisorJob()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstCounterText = findViewById(R.id.firstCounter)
        secondCounterText = findViewById(R.id.secondCounter)
        thirdCounterText = findViewById(R.id.thirdCounter)
        stopButton = findViewById(R.id.stopBtn)
        launchButton = findViewById(R.id.launchButton)
        launchButton.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                lifecycleScope.launch(job + Dispatchers.Default) {
                    launchCounter1()
                    launchCounter2()
                    launchCounter3()
                }
            }
        }

        stopButton.setOnClickListener {
            isRunning = false
            job.cancel()
        }
    }

    private suspend fun launchCounter1() {
        while (isRunning) {
            withContext(Dispatchers.Main) {
                counter1++
                firstCounterText.text = counter1.toString()
            }
            delay(1000)
        }
    }

    private suspend fun launchCounter2() {
        while (isRunning) {
           withContext(Dispatchers.Main) {
               counter2 += 5
               secondCounterText.text = counter2.toString()
           }
            delay(1000)
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun launchCounter3() {
        while (isRunning) {
           withContext(Dispatchers.Main) {
               counter3 = counter3.multiply(counter3)
               thirdCounterText.text = counter3.toString()
           }
            delay(1000)
        }
    }
}