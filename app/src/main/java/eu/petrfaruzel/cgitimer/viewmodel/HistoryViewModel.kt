package eu.petrfaruzel.cgitimer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.petrfaruzel.cgitimer.database.DataRepository
import eu.petrfaruzel.cgitimer.dto.Lap
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val repository: DataRepository = DataRepository

    val lapsHistory : LiveData<List<Lap>> = repository.getLaps(20)

}