package com.example.news

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

class CustomAdapter(private val itemList: MutableList<String>, private val inputTypeList: MutableList<Int>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val inputedText = Array(itemList.size) { _ -> "" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textInputLayout?.hint = itemList[position]
        holder.textInputLayout?.editText?.inputType = 1 or inputTypeList[position]
        if (inputTypeList[position] == 128) {
            holder.textInputLayout?.setPasswordVisibilityToggleEnabled(true)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getInputedText(): Array<String> {
        return inputedText
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var textInputLayout: TextInputLayout? = null
        init {
            textInputLayout = itemView.findViewById(R.id.textInputLayout)
            textInputLayout?.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    inputedText[adapterPosition] = p0.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }
    }

}