package eu.petrfaruzel.cgitimer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.petrfaruzel.cgitimer.database.DataRepository
import eu.petrfaruzel.cgitimer.database.DataRepository.runningStartTimestamp
import eu.petrfaruzel.cgitimer.database.DataRepository.runningState
import eu.petrfaruzel.cgitimer.dto.Lap
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {

    private val repository: DataRepository = DataRepository

    // Are stopwatches running?
    private val _runningState: MutableLiveData<Boolean> by lazy { MutableLiveData(repository.runningState) }
    val runningState : LiveData<Boolean> = _runningState

    // For how long stopwatches ran before being stopped
    private val _runningTime: MutableLiveData<Long> by lazy { MutableLiveData(repository.runningTime) }
    val runningTime : LiveData<Long> = _runningTime

    // When were stopwatches turned on
    private val _runningStartTimestamp: MutableLiveData<Long> by lazy { MutableLiveData(repository.runningStartTimestamp) }
    val runningStartTimestamp : LiveData<Long> = _runningStartTimestamp

    // Active session laps
    val activeLaps : LiveData<List<Lap>> = repository.getActiveLaps()

    fun setRunning(value: Boolean){
        // On turning off
        if(!value) setRunningTime(Calendar.getInstance().timeInMillis - runningStartTimestamp.value!! + runningTime.value!!)

        // On turning on
        if (value) setRunningStartTimestamp(Calendar.getInstance().timeInMillis)

        _runningState.value = value
        repository.runningState = value // Persistence
    }

    fun addLap(){
        val lap = if(runningState.value!!) {
            Lap(null, Calendar.getInstance().timeInMillis - runningStartTimestamp.value!! + runningTime.value!!, Calendar.getInstance().timeInMillis, true)
        } else {
            Lap(null, runningTime.value!!, Calendar.getInstance().timeInMillis, true)
        }

        viewModelScope.launch {
            repository.addLap(lap) // Persistence
        }

    }

    private fun setRunningTime(runningTime : Long){
        _runningTime.value = runningTime
        repository.runningTime = runningTime
    }

    private fun setRunningStartTimestamp(timestamp: Long){
        _runningStartTimestamp.value = timestamp
        repository.runningStartTimestamp = timestamp
    }

    fun reset(){
        setRunningTime(0)
        setRunningStartTimestamp(Calendar.getInstance().timeInMillis)
        viewModelScope.launch {
            repository.deactivateLaps()
        }
    }

}