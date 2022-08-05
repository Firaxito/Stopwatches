package eu.petrfaruzel.cgitimer.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{

        @SuppressLint("SimpleDateFormat")
        fun millisDifferenceToTimeString(startTime: Long, endTime: Long) : String{
            val timeDifference = endTime - startTime
            if (timeDifference >= 1000 * 60 * 60) // One hour
                return (SimpleDateFormat("HH:mm:ss.SSS")).format(Date(endTime - startTime));
            else
                return (SimpleDateFormat("mm:ss.SSS")).format(Date(endTime - startTime));
        }

        fun millisToTimeString(millis  : Long) : String {
            return millisDifferenceToTimeString(0, millis)
        }

        @SuppressLint("SimpleDateFormat")
        fun millisToDateString(millis : Long) : String {
            return (SimpleDateFormat("dd-MM-yyyy")).format(Date(millis));
        }
    }
}