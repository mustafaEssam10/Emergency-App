package com.example.emergencyapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val scale = resources.displayMetrics.density
            val desiredPx = (16 * scale + 0.5f).toInt()
            v.setPadding(
                systemBars.left + desiredPx,
                systemBars.top + desiredPx,
                systemBars.right + desiredPx,
                systemBars.bottom + desiredPx
            )
            insets
        }

        val emergencies = arrayListOf<Emergency>()
        emergencies.add(Emergency(R.drawable.police, "Police", "122"))
        emergencies.add(Emergency(R.drawable.child, "Child Rescue", "16000"))
        emergencies.add(Emergency(R.drawable.fire, "Fire", "180"))
        emergencies.add(Emergency(R.drawable.hospital, "Hospital", "123"))

        val adapter = EmergencyAdapter(this, emergencies)

        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.adapter = adapter

    }

    override fun onBackPressed() {
        val exit = ExitDialog()
        exit.isCancelable = false
        exit.show(supportFragmentManager, null)
    }
}