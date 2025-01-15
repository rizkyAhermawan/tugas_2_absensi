package com.example.absensi_rizky
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData

@Dao
interface AbsensiDao {
    @Insert
    suspend fun insert(absensi: Absensi)

    @Update
    suspend fun update(absensi: Absensi)

    @Delete
    suspend fun delete(absensi: Absensi)

    @Query("SELECT * FROM absensi")
    fun getAllAbsensi(): LiveData<List<Absensi>>

    @Query("SELECT * FROM absensi WHERE id = :id")
    fun getAbsensiById(id: Int): LiveData<Absensi>
}
