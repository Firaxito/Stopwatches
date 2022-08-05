package eu.petrfaruzel.cgitimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import eu.petrfaruzel.cgitimer.dto.Lap

@Dao
interface LapDao {

    @Query("SELECT * FROM Lap ORDER BY id DESC")
    fun getAll(): LiveData<List<Lap>>

    @Query("SELECT * FROM Lap ORDER BY id DESC LIMIT :limit")
    fun getLaps(limit: Int): LiveData<List<Lap>>

    @Query("SELECT * FROM Lap WHERE active = 1 ORDER BY id DESC")
    fun getActive(): LiveData<List<Lap>>

    @Insert
    suspend fun insertAll(vararg laps: Lap)

    @Delete
    suspend fun delete(lap: Lap)

    @Query("DELETE FROM Lap")
    suspend fun deleteAll()

    @Query("UPDATE Lap SET active = 0")
    suspend fun deactivateAll()
}