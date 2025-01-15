package com.example.absensi_rizky

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "absensi")
data class Absensi(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val tanggal: String,
    val status: String // Misalnya "Hadir", "Izin", "Sakit"
)
