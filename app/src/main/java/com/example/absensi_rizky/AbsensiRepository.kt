package com.example.absensi_rizky

class AbsensiRepository(private val absensiDao: AbsensiDao) {
    val allAbsensi: LiveData<List<Absensi>> = absensiDao.getAllAbsensi()

    suspend fun insert(absensi: Absensi) {
        absensiDao.insert(absensi)
    }

    suspend fun update(absensi: Absensi) {
        absensiDao.update(absensi)
    }

    suspend fun delete(absensi: Absensi) {
        absensiDao.delete(absensi)
    }
}
