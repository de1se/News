package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.news.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var activityBinding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

}