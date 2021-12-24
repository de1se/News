package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.news.databinding.MainMenuBinding

class MainMenu : Fragment() {
    private lateinit var activityBinding: MainMenuBinding
    private val newsFragment = NewsFragment()
    private val favoriteFragment = FavoriteFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(newsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = MainMenuBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityBinding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.newsFragment -> replaceFragment(newsFragment)
                R.id.favoriteFragment -> replaceFragment(favoriteFragment)
                R.id.profileFragment -> replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}