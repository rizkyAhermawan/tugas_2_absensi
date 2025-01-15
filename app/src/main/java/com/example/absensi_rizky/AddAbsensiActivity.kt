package com.example.absensi_rizky

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddAbsensiActivity : AppCompatActivity() {

    private lateinit var absensiViewModel: AbsensiViewModel

    private lateinit var etNama: EditText
    private lateinit var etTanggal: EditText
    private lateinit var spinnerStatus: Spinner
    private lateinit var btnSave: Button

    private var absensiId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_absensi)

        // Inisialisasi UI
        etNama = findViewById(R.id.etNama)
        etTanggal = findViewById(R.id.etTanggal)
        spinnerStatus = findViewById(R.id.spinnerStatus)
        btnSave = findViewById(R.id.btnSave)

        // Menyiapkan spinner status
        val statuses = listOf("Hadir", "Izin", "Sakit")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = adapter

        // Mendapatkan ViewModel
        absensiViewModel = ViewModelProvider(this).get(AbsensiViewModel::class.java)

        // Cek jika ada ID absensi yang dikirim (untuk edit)
        absensiId = intent.getIntExtra("EXTRA_ID", -1)
        if (absensiId != -1) {
            // Ambil data absensi untuk edit
            absensiViewModel.getAbsensiById(absensiId!!).observe(this, Observer { absensi ->
                etNama.setText(absensi.nama)
                etTanggal.setText(absensi.tanggal)
                spinnerStatus.setSelection(statuses.indexOf(absensi.status))
            })
        }

        // Handle tombol Save (untuk menyimpan atau mengedit absensi)
        btnSave.setOnClickListener {
            val nama = etNama.text.toString()
            val tanggal = etTanggal.text.toString()
            val status = spinnerStatus.selectedItem.toString()

            if (nama.isNotEmpty() && tanggal.isNotEmpty()) {
                if (absensiId != -1) {
                    // Edit absensi
                    val updatedAbsensi = Absensi(id = absensiId!!, nama = nama, tanggal = tanggal, status = status)
                    absensiViewModel.update(updatedAbsensi)
                } else {
                    // Tambah absensi baru
                    val newAbsensi = Absensi(nama = nama, tanggal = tanggal, status = status)
                    absensiViewModel.insert(newAbsensi)
                }
                finish() // Kembali ke layar utama setelah menyimpan
            } else {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
