package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentNewsBinding
import com.google.gson.Gson


class NewsFragment : Fragment() {
    private lateinit var activityBinding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = activityBinding.newsList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerAdapter(getJsonFile())
    }

    private fun getJsonFile(): Array<Article> {
        val jsonString: String =
            requireContext().assets
                .open("Article.json")
                .bufferedReader().use {
                    it.readText()
                }
        return Gson().fromJson(jsonString, Array<Article>::class.java)
    }

}