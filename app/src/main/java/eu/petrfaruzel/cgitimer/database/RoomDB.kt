package eu.petrfaruzel.cgitimer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.petrfaruzel.cgitimer.App
import eu.petrfaruzel.cgitimer.dao.LapDao
import eu.petrfaruzel.cgitimer.dto.Lap

@Database(entities = [Lap::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun lapDao(): LapDao

    companion object {

        private var database : RoomDB? = null

        fun getInstance() : RoomDB {
            if (database == null)
                synchronized(RoomDB::class){
                    database = buildRoomDB()
                }
            return database!!
        }

        private fun buildRoomDB() = Room.databaseBuilder(App.getContext(), RoomDB::class.java, "room-laps-db").fallbackToDestructiveMigration().build()

    }
}

