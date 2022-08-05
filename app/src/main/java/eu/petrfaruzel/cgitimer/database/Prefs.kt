package eu.petrfaruzel.cgitimer.database

import android.content.Context
import android.content.SharedPreferences
import eu.petrfaruzel.cgitimer.App
import java.util.*

object Prefs {

    private const val PREFS_FILENAME = "eu.petrfaruzel.cgitimer.prefs"

    private val prefs = App.getContext().getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    private const val IS_RUNNING = "is_running"
    private const val RUNNING_TIME = "running_time"
    private const val START_TIMESTAMP = "start_timestamp"

    var running: Boolean
        get() = prefs.getBoolean(IS_RUNNING, false)
        set(value) = prefs.edit().putBoolean(IS_RUNNING, value).apply()

    var runningTime: Long
        get() = prefs.getLong(RUNNING_TIME, 0)
        set(value) = prefs.edit().putLong(RUNNING_TIME, value).apply()

    var startTimestamp: Long
        get() = prefs.getLong(START_TIMESTAMP, Calendar.getInstance().timeInMillis)
        set(value) = prefs.edit().putLong(START_TIMESTAMP, value).apply()


}