package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var activityBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentProfileBinding.inflate(inflater, container, false)
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