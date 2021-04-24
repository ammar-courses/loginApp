package com.s95ammar.loginapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.ui.activity.SharedViewModel
import com.s95ammar.loginapp.ui.login.common.LoginUiEvent
import com.s95ammar.loginapp.ui.welcome.WelcomeFragment
import com.s95ammar.loginapp.util.Event

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginEditText = view.findViewById<EditText>(R.id.login_edit_text)
        val passwordEditText = view.findViewById<EditText>(R.id.password_edit_text)
        val loginButton = view.findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            viewModel.onLogin(
                login = loginEditText.text.toString(),
                password = passwordEditText.text.toString()
            )
        }

        viewModel.login.observe(viewLifecycleOwner) { login ->
            sharedViewModel.setLogin(login)
            navigateToWelcomeFragment()
//            navigateToWelcomeFragment(login) // solution with arguments
        }

        viewModel.uiEvent.observe(viewLifecycleOwner) { event ->
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

    private fun handleEvent(event: Event<LoginUiEvent>) {
        event.getIfNotHandled()?.let { loginEvent ->
            when (loginEvent) {
                is LoginUiEvent.Loading -> {
                    val progressBar = view?.findViewById<ProgressBar>(R.id.login_loading_progress_bar)
                    val loginButton = view?.findViewById<Button>(R.id.login_button)

                    progressBar?.isVisible = loginEvent.isLoading
                    loginButton?.isGone = loginEvent.isLoading
                }
                is LoginUiEvent.LoginError -> {
                    Toast.makeText(requireContext(), loginEvent.errorStringId, Toast.LENGTH_LONG).show()
                }
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

}