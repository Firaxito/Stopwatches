package eu.petrfaruzel.cgitimer.database

import eu.petrfaruzel.cgitimer.dto.Lap

object DataRepository {

    // Shared preferences data
    var runningState: Boolean
        get() = Prefs.running
        set(value) { Prefs.running = value }

    var runningTime: Long
        get() = Prefs.runningTime
        set(value) { Prefs.runningTime = value }

    var runningStartTimestamp: Long
        get() = Prefs.startTimestamp
        set(value) { Prefs.startTimestamp = value }

    // Room data
    fun getAllLaps() = RoomDB.getInstance().lapDao().getAll()
    fun getLaps(count : Int) = RoomDB.getInstance().lapDao().getLaps(count)
    fun getActiveLaps() = RoomDB.getInstance().lapDao().getActive()
    suspend fun addLap(lap : Lap) = RoomDB.getInstance().lapDao().insertAll(lap)
    suspend fun clearLaps() = RoomDB.getInstance().lapDao().deleteAll()
    suspend fun deactivateLaps() = RoomDB.getInstance().lapDao().deactivateAll()

}