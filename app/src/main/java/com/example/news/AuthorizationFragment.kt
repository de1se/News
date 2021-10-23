package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {
    private lateinit var activityBinding: FragmentAuthorizationBinding
    private lateinit var customUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentAuthorizationBinding.inflate(inflater, container,false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountDataManager = AccountDataManager(ViewModelProvider(this).get(UserViewModel::class.java), requireContext())
        val recyclerView: RecyclerView = activityBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CustomAdapter(getHintList(), getInputTypeList())
        val adapter = recyclerView.adapter as CustomAdapter

        activityBinding.authorizationButton.setOnClickListener {
            val inputedText = adapter.getInputedText()
            if (accountDataManager.isValidData(inputedText[0], inputedText[2])) {
                accountDataManager.logInAccount()
                this.findNavController().navigate(R.id.action_authorizationFragment_to_mainMenu)
            } else {
                DataValidator.showToast(getString(R.string.ERROR_MESSAGE_ACCOUNT_DOES_NOT_EXIST), requireContext())
            }
        }

        activityBinding.switchToRegistrationButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }

    private fun getHintList(): MutableList<String> {
        val authorizationList: MutableList<String> = resources.getStringArray(R.array.hintList).toMutableList()
        authorizationList.removeAt(1)
        authorizationList.removeAt(2)
        return authorizationList
    }

    private fun getInputTypeList(): MutableList<Int> {
        val authorizationList: MutableList<Int> = resources.getIntArray(R.array.inputTypeList).toMutableList()
        authorizationList.removeAt(1)
        authorizationList.removeAt(2)
        return authorizationList
    }
}