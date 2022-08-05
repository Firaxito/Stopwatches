package eu.petrfaruzel.cgitimer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.petrfaruzel.cgitimer.R
import eu.petrfaruzel.cgitimer.adapters.LapsAdapter
import eu.petrfaruzel.cgitimer.databinding.FragmentMainBinding
import eu.petrfaruzel.cgitimer.utils.Utils
import eu.petrfaruzel.cgitimer.utils.setDivider
import eu.petrfaruzel.cgitimer.viewmodel.MainViewModel
import java.util.*

class MainFragment : Fragment() {

    private val model: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding
    private var timer: Timer? = null

    private val lapsAdapter: LapsAdapter = LapsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.recycler.adapter = lapsAdapter
        binding.recycler.setDivider()

        model.runningState.observe(viewLifecycleOwner) {
            if (it) runTimer() else stopTimer()

            if (it) binding.btnStartStop.text = getString(R.string.stop)
            else binding.btnStartStop.text = getString(R.string.start)

            // Initialize displayed time text
            if(model.runningState.value!!)
                // On start running
                binding.tvTime.text = Utils.millisDifferenceToTimeString(model.runningStartTimestamp.value ?: 0, Calendar.getInstance().timeInMillis + (model.runningTime.value ?: 0))
            else {
                // On stop running
                binding.tvTime.text = Utils.millisToTimeString(model.runningTime.value ?: 0)
            }
        }

        model.activeLaps.observe(viewLifecycleOwner){
            lapsAdapter.changeData(it)
            binding.recycler.scrollToPosition(0)
        }

        binding.btnStartStop.setOnClickListener {
            model.setRunning(!model.runningState.value!!)
        }

        binding.btnLap.setOnClickListener {
            model.addLap()
        }

        binding.btnReset.setOnClickListener {
            model.reset()
            binding.tvTime.text = "00:00.000"
        }

        return binding.root
    }

    override fun onPause() {
        stopTimer()
        super.onPause()
    }

    override fun onResume() {
        if (model.runningState.value!! && timer==null) runTimer()
        super.onResume()
    }

    private fun runTimer(){
        if (timer == null) timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    binding.tvTime.text = Utils.millisDifferenceToTimeString(model.runningStartTimestamp.value ?: 0, Calendar.getInstance().timeInMillis + (model.runningTime.value ?: 0))
                }
            }
        }, 0, 37L)
    }

    private fun stopTimer(){
        timer?.cancel()
        timer?.purge()
        timer = null
    }

}