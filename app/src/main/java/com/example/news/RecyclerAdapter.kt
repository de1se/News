package com.example.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.RecyclerItemBinding

class RecyclerAdapter(private val article: Array<Article>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val recyclerBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(recyclerBinding.root) {
        var headerView: TextView? = null

        init {
            headerView = recyclerBinding.header
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headerView?.text = article[position].heading
    }

    override fun getItemCount(): Int {
        return article.size
    }
}