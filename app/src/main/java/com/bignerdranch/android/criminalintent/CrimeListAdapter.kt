package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeDetailBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemSeriousCrimeBinding

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(crime: Crime)
}

class CrimeHolder(
        private val binding: ListItemCrimeBinding
) : BaseViewHolder(binding.root) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                    binding.root.context,
                    "${crime.title} clicked!",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class SeriousCrimeHolder(
        private val binding: ListItemSeriousCrimeBinding
) : BaseViewHolder(binding.root) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.root.setOnClickListener {
            Toast.makeText(
                    binding.root.context,
                    "${crime.title} clicked!",
                    Toast.LENGTH_SHORT
            ).show()
        }
        binding.callThePoliceButton.setOnClickListener {
            Toast.makeText(
                    binding.root.context,
                    "Police has been called!",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
        private val crimes: List<Crime>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = if (viewType == 0) {
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            CrimeHolder(binding)
        } else {
            val binding = ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
            SeriousCrimeHolder(binding)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) 1 else 0
    }
}
