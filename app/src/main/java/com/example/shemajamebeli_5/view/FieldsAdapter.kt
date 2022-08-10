package com.example.shemajamebeli_5.view

import android.annotation.SuppressLint
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli_5.databinding.LayoutChooserBdayBinding
import com.example.shemajamebeli_5.databinding.LayoutChooserGenderBinding
import com.example.shemajamebeli_5.databinding.LayoutInputTypeBinding
import com.example.shemajamebeli_5.model.DataFields
import com.example.shemajamebeli_5.setImage

class FieldsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val INPUT_TYPE_INPUT = 1
        const val INPUT_TYPE_GENDER = 2
        const val INPUT_TYPE_DATE = 3
    }

    var fieldsList = listOf<DataFields>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            INPUT_TYPE_INPUT -> InputViewHolder(LayoutInputTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            INPUT_TYPE_GENDER -> GenderViewHolder(LayoutChooserGenderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> DateViewHolder(
                LayoutChooserBdayBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InputViewHolder -> holder.bind()
            is GenderViewHolder -> holder.bind()
            is DateViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = fieldsList.size

    override fun getItemViewType(position: Int): Int {
        when (fieldsList[position][0][0].fieldType?.lowercase()) {
            "input" -> {
                return INPUT_TYPE_INPUT
            }
            "chooser" -> {
                when (fieldsList[position][0][0].hint?.lowercase()) {
                    "birthday" -> {
                        return INPUT_TYPE_DATE
                    }
                    "gender" -> {
                        return INPUT_TYPE_GENDER
                    }
                }
            }
        }
        return -1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: DataFields) {
        fieldsList = listOf(newList)
        notifyDataSetChanged()
    }

    inner class InputViewHolder(private val binding: LayoutInputTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = fieldsList[adapterPosition]
            binding.apply {
                etInput.apply {
                    when (fieldsList[adapterPosition][0][0].keyboard) {
                        "text" -> {
                            inputType = InputType.TYPE_CLASS_TEXT
                        }
                        "number" -> {
                            inputType = InputType.TYPE_CLASS_NUMBER
                        }
                    }
                    hint = currentItem[0][0].hint
                }
                ivIcon.setImage("https://icon-library.com/images/edit-icon-png/edit-icon-png-0.jpg")
                etInput.isEnabled = currentItem[0][0].isActive == true
            }
        }
    }

    inner class DateViewHolder(private val binding: LayoutChooserBdayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = fieldsList[adapterPosition]
            binding.apply {
                dpBirthday.updateDate(2002, 4, 28)
                ivIcon.setImage("https://icon-library.com/images/edit-icon-png/edit-icon-png-0.jpg")
                dpBirthday.isEnabled = currentItem[0][0].isActive == true
                tvHint.text = currentItem[0][0].hint
            }
        }
    }

    inner class GenderViewHolder(private val binding: LayoutChooserGenderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = fieldsList[adapterPosition]
            binding.apply {
                ivIcon.setImage("https://icon-library.com/images/edit-icon-png/edit-icon-png-0.jpg")
                spGender.isEnabled = currentItem[0][0].isActive == true
                tvHint.text = currentItem[0][0].hint
            }
        }
    }
}