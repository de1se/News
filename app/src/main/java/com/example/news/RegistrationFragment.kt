package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private lateinit var activityBinding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentRegistrationBinding.inflate(inflater, container,false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountDataManager = AccountDataManager(requireContext())
        if (accountDataManager.checkIsLogged()) {
            this.findNavController().navigate(R.id.action_authorizationFragment_to_mainMenu)
        }

        val recyclerView: RecyclerView = activityBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CustomAdapter(getHintList(), getInputTypeList())
        val adapter = recyclerView.adapter as CustomAdapter

        activityBinding.registrationButton.setOnClickListener {
            if (activityBinding.termsOfService.isChecked) {
                val inputedText = adapter.getInputedText()
                val newUserData = AccountData(inputedText[0], inputedText[1], inputedText[2], inputedText[3])
                if (DataValidator.isValidInput(requireContext(), newUserData)){
                    accountDataManager.registerAccount(newUserData)
                    this.findNavController().navigate(R.id.action_authorizationFragment_to_mainMenu)
                }
            }
        }

        activityBinding.switchToAuthorizationButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }

    private fun getHintList(): MutableList<String> {
        return resources.getStringArray(R.array.hintList).toMutableList()
    }

    private fun getInputTypeList(): MutableList<Int> {
        return resources.getIntArray(R.array.inputTypeList).toMutableList()
    }
}