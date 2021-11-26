package com.example.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentAuthorizationBinding
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast

class AuthorizationFragment : Fragment() {
    private lateinit var activityBinding: FragmentAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountDataManager = AccountDataManager(requireContext())
        setupListeners()

        activityBinding.authorizationButton.setOnClickListener {
            if (isValidInput()) {
                accountDataManager.logInAccount(
                    (activity as MainActivity).accountDao.findByLogin(
                        activityBinding.textInputLayoutLogin.editText?.text.toString()
                    )
                )
                this.findNavController().navigate(R.id.action_authorizationFragment_to_mainMenu)
            }
        }

        activityBinding.switchToRegistrationButton.setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }

    private fun isValidInput(): Boolean {
        return if (DataValidator.validateLoginSignIn(
                activityBinding.textInputLayoutLogin,
                (activity as MainActivity).accountDao,
                requireContext()
            ) &&
            DataValidator.validatePassword(
                activityBinding.textInputLayoutLogin,
                activityBinding.textInputLayoutPassword,
                (activity as MainActivity).accountDao,
                requireContext()
            )
        ) {
            true
        } else {
            Toast.makeText(
                context,
                context?.getString(R.string.ERROR_MESSAGE_INVALID_LOGIN_OR_PASSWORD),
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun setupListeners() {
        activityBinding.textInputLayoutLogin.editText?.addTextChangedListener(
            TextFieldValidation(
                activityBinding.textInputLayoutLogin
            )
        )
        activityBinding.textInputLayoutPassword.editText?.addTextChangedListener(
            TextFieldValidation(
                activityBinding.textInputLayoutPassword
            )
        )
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id) {
                R.id.textInputLayoutLogin -> {
                    DataValidator.validateLoginSignIn(
                        activityBinding.textInputLayoutLogin,
                        (activity as MainActivity).accountDao, requireContext()
                    )
                }
                R.id.textInputLayoutPassword -> {
                    DataValidator.validatePassword(
                        activityBinding.textInputLayoutPassword,
                        requireContext()
                    )
                }
            }
        }
    }
}
