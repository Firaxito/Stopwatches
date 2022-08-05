package eu.petrfaruzel.cgitimer.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.petrfaruzel.cgitimer.R
import eu.petrfaruzel.cgitimer.dto.Lap
import eu.petrfaruzel.cgitimer.utils.Utils

class LapsAdapter(private var laps: List<Lap> = listOf()) :
    RecyclerView.Adapter<LapsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val laptime: TextView
        val timestamp: TextView

        init {
            laptime = view.findViewById(R.id.item_lap_laptime)
            timestamp = view.findViewById(R.id.item_lap_timestamp)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_lap, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.laptime.text = Utils.millisToTimeString(laps[position].time)
        viewHolder.timestamp.text = "${Utils.millisToDateString(laps[position].timestamp)}\n${Utils.millisToTimeString(laps[position].timestamp)}"
    }

    override fun getItemCount() = laps.size

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(laps: List<Lap> ){
        val wasOneAdded = this.laps.size == laps.size-1
        this.laps = laps
        if(wasOneAdded){
            notifyItemInserted(0)
        } else {
            // Initialization after shutdown with running stopwatches
            notifyDataSetChanged()
        }
    }

}
