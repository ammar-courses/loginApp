package com.s95ammar.loginapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.databinding.LoginFragmentBinding
import com.s95ammar.loginapp.ui.activity.SharedViewModel
import com.s95ammar.loginapp.ui.login.common.LoginUiEvent
import com.s95ammar.loginapp.ui.welcome.WelcomeFragment
import com.s95ammar.loginapp.util.observeEvent

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: LoginFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            viewModel.onLogin(
                login = binding.loginEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        viewModel.login.observe(viewLifecycleOwner) { login ->
            sharedViewModel.setLogin(login)
            navigateToWelcomeFragment()
//            navigateToWelcomeFragment(login) // solution with arguments
        }

        viewModel.uiEvent.observeEvent(viewLifecycleOwner) { event ->
            handleEvent(event)
        }
/*
        viewModel.loginErrorEvent.observe(viewLifecycleOwner) { loginErrorEvent ->
            loginErrorEvent.getIfNotHandled()?.let { errorMessageId ->
                Toast.makeText(requireContext(), errorMessageId, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loadingEvent.observe(viewLifecycleOwner) { loadingEvent ->
            loadingEvent.getIfNotHandled()?.let { isLoading ->
                val progressBar = view.findViewById<ProgressBar>(R.id.loading_progress_bar)
                progressBar.isVisible = isLoading
                loginButton.isGone = isLoading
            }
        }
*/
    }

    private fun handleEvent(loginEvent: LoginUiEvent) {
        when (loginEvent) {
            is LoginUiEvent.Loading -> {
                binding.loginLoadingProgressBar.isVisible = loginEvent.isLoading
                binding.loginButton.isGone = loginEvent.isLoading
            }
            is LoginUiEvent.LoginError -> {
                Toast.makeText(requireContext(), loginEvent.errorStringId, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToWelcomeFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, WelcomeFragment())
            .commit()
    }

/*
  // solution with arguments
  private fun navigateToWelcomeFragment(login: String) {
        val welcomeFragment = WelcomeFragment.newInstance(login)

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, welcomeFragment)
            .commit()
    }
*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
