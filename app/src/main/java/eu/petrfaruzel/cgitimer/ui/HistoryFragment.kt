package eu.petrfaruzel.cgitimer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import eu.petrfaruzel.cgitimer.R
import eu.petrfaruzel.cgitimer.adapters.LapsAdapter
import eu.petrfaruzel.cgitimer.databinding.FragmentHistoryBinding
import eu.petrfaruzel.cgitimer.databinding.FragmentMainBinding
import eu.petrfaruzel.cgitimer.utils.setDivider
import eu.petrfaruzel.cgitimer.viewmodel.HistoryViewModel
import eu.petrfaruzel.cgitimer.viewmodel.MainViewModel


class HistoryFragment : Fragment() {

    private val model: HistoryViewModel by viewModels()

    private lateinit var binding: FragmentHistoryBinding

    private val historyAdapter : LapsAdapter = LapsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.recycler.adapter = historyAdapter
        binding.recycler.setDivider()

        model.lapsHistory.observe(viewLifecycleOwner){
            historyAdapter.changeData(it)
        }

        return binding.root
    }

}