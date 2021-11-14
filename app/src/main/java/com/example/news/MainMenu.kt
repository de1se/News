package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentMainMenuBinding

class MainMenu : Fragment() {
    private lateinit var activityBinding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountDataManager = AccountDataManager(requireContext())

        activityBinding.exitFromAccountButton.setOnClickListener {
            accountDataManager.logOutAccount()
            this.findNavController().navigate(R.id.action_mainMenu_to_authorizationFragment)
        }
    }


}