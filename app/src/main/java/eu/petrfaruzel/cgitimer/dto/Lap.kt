package eu.petrfaruzel.cgitimer.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity
data class Lap (
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "active") val active: Boolean
 )