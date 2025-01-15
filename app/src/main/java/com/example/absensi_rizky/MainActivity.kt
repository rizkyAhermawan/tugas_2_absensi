package com.example.absensi_rizky

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var absensiViewModel: AbsensiViewModel
    private lateinit var adapter: AbsensiAdapter
    private lateinit var rvAbsensi: RecyclerView
    private lateinit var btnAddAbsensi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAbsensi = findViewById(R.id.rvAbsensi)
        btnAddAbsensi = findViewById(R.id.btnAddAbsensi)

        rvAbsensi.layoutManager = LinearLayoutManager(this)
        adapter = AbsensiAdapter(emptyList()) { absensi ->

            val intent = Intent(this, AddAbsensiActivity::class.java)
            intent.putExtra("EXTRA_ID", absensi.id)
            startActivity(intent)
        }
        rvAbsensi.adapter = adapter

        absensiViewModel = ViewModelProvider(this).get(AbsensiViewModel::class.java)
        absensiViewModel.allAbsensi.observe(this, Observer { absensiList ->
            adapter.submitList(absensiList)
        })

        // Buka form untuk menambah absensi baru
        btnAddAbsensi.setOnClickListener {
            val intent = Intent(this, AddAbsensiActivity::class.java)
            startActivity(intent)
        }
    }
    adapter = AbsensiAdapter(emptyList(), { absensi ->
        // Edit absensi
        val intent = Intent(this, AddAbsensiActivity::class.java)
        intent.putExtra("EXTRA_ID", absensi.id)
        startActivity(intent)
    }, { absensi ->

        absensiViewModel.delete(absensi)
    })

}
