package com.example.news

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private lateinit var activityBinding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountDataManager = AccountDataManager(requireContext())
        if (accountDataManager.checkIsLogged()) {
            this.findNavController().navigate(R.id.action_registrationFragment_to_mainMenu)
        }

        setupListeners()

        activityBinding.termsOfService.setOnClickListener {
            activityBinding.registrationButton.isEnabled = activityBinding.termsOfService.isChecked
        }

        activityBinding.registrationButton.setOnClickListener {
            val newAccountData = Account(
                0,
                activityBinding.textInputLayoutLogin.toString(),
                activityBinding.textInputLayoutEmail.toString(),
                activityBinding.textInputLayoutPassword.toString()
            )
            if (isValidInput()) {
                accountDataManager.logInAccount(newAccountData)
                (activity as MainActivity).accountDao.insertNewAccount(newAccountData)
                this.findNavController().navigate(R.id.action_registrationFragment_to_mainMenu)
            }
        }

        activityBinding.switchToAuthorizationButton.setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }

    private fun isValidInput(): Boolean =
        DataValidator.validateLoginSignUp(
            activityBinding.textInputLayoutLogin,
            (activity as MainActivity).accountDao,
            requireContext()
        ) &&
        DataValidator.validateEmail(
            activityBinding.textInputLayoutEmail,
            (activity as MainActivity).accountDao,
            requireContext()
        ) &&
        DataValidator.validatePassword(
            activityBinding.textInputLayoutPassword,
            requireContext()
        ) &&
        DataValidator.validRepeatedPassword(
            activityBinding.textInputLayoutPassword,
            activityBinding.textInputLayoutRepeatedPassword,
            requireContext()
        )

    private fun setupListeners() {
        activityBinding.textInputLayoutLogin.editText?.addTextChangedListener(
            TextFieldValidation(
                activityBinding.textInputLayoutLogin
            )
        )
        activityBinding.textInputLayoutEmail.editText?.addTextChangedListener(
            TextFieldValidation(
                activityBinding.textInputLayoutEmail
            )
        )
        activityBinding.textInputLayoutPassword.editText?.addTextChangedListener(
            TextFieldValidation(
                activityBinding.textInputLayoutPassword
            )
        )
        activityBinding.textInputLayoutRepeatedPassword.editText?.addTextChangedListener(
            TextFieldValidation(activityBinding.textInputLayoutRepeatedPassword)
        )
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id) {
                R.id.textInputLayoutLogin -> {
                    DataValidator.validateLoginSignUp(
                        activityBinding.textInputLayoutLogin,
                        (activity as MainActivity).accountDao,
                        requireContext()
                    )
                }
                R.id.textInputLayoutEmail -> {
                    DataValidator.validateEmail(
                        activityBinding.textInputLayoutEmail,
                        (activity as MainActivity).accountDao,
                        requireContext()
                    )
                }
                R.id.textInputLayoutPassword -> {
                    DataValidator.validatePassword(
                        activityBinding.textInputLayoutPassword,
                        requireContext()
                    )
                }
                R.id.textInputLayoutRepeatedPassword -> {
                    DataValidator.validRepeatedPassword(
                        activityBinding.textInputLayoutPassword,
                        activityBinding.textInputLayoutRepeatedPassword,
                        requireContext()
                    )
                }
            }
        }
    }

}