package com.example.hospital_database.ui.patient_appointment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_database.R
import com.example.hospital_database.network.response.PatientAppointmentResponse
import java.text.SimpleDateFormat

class AppointmentRecyclerAdapter(
    private val context: Context,
    private val data: PatientAppointmentResponse,
    private val listener: OnItemClickListener
    ): RecyclerView.Adapter<AppointmentRecyclerAdapter.AppointmentViewHolder>() {
    inner class AppointmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val textDep: TextView = itemView.findViewById(R.id.item_patient_dep)
        val textDoc: TextView= itemView.findViewById(R.id.item_patient_doc)
        val textDate: TextView= itemView.findViewById(R.id.item_patient_date)
        val textDay: TextView= itemView.findViewById(R.id.item_patient_day)
        val textTime: TextView= itemView.findViewById(R.id.item_patient_time)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(data[position].cliId)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(cliId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_patient_appointment, parent, false))
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val dateFormatter= SimpleDateFormat("MMM/dd")
        holder.apply{
            textDep.text= data[position].depName
            textDoc.text= data[position].docName
            textDate.text= dateFormatter.format(data[position].cliDate)
            textDay.text= when(data[position].cliDay){
                1 -> "???"
                2 -> "???"
                3 -> "???"
                4 -> "???"
                5 -> "???"
                else -> "?"
            }
            textTime.text= when(data[position].cliTime){
                0 -> "??????"
                else -> "??????"
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}