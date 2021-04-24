package com.s95ammar.loginapp.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.ui.activity.SharedViewModel
import com.s95ammar.loginapp.ui.welcome.common.WelcomeUiEvent
import com.s95ammar.loginapp.util.observeEvent

class WelcomeFragment : Fragment() {

/*
    // solution with arguments
    companion object {
        fun newInstance(login: String) = WelcomeFragment().apply {
            arguments = bundleOf(WelcomeScreenKeys.KEY_LOGIN to login)
        }
    }
*/

    private val viewModel: WelcomeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val welcomeTextView = view.findViewById<TextView>(R.id.welcome_text_view)
        val logoutButton = view.findViewById<Button>(R.id.logout_button)

        sharedViewModel.login.observe(viewLifecycleOwner) { login ->
            welcomeTextView.text = getString(R.string.format_welcome, login)
        }

        viewModel.uiEvent.observeEvent(viewLifecycleOwner) { event ->
            handleEvent(event)
        }

        logoutButton.setOnClickListener {
            viewModel.onLogout()
        }
/*
        val imageView = view.findViewById<ImageView>(R.id.login_image_view)
        imageView.setImageResource(R.drawable.ic_key)
*/
/*
      // solution with arguments
      val login = arguments?.getString(WelcomeScreenKeys.KEY_LOGIN).orEmpty()
        welcomeTextView.text = "Welcome $login (by arguments)"
*/
    }

    private fun handleEvent(welcomeEvent: WelcomeUiEvent) {
        when (welcomeEvent) {
            is WelcomeUiEvent.Loading -> {
                val logoutButton = view?.findViewById<Button>(R.id.logout_button)
                val progressBar = view?.findViewById<ProgressBar>(R.id.welcome_loading_progress_bar)

                logoutButton?.isInvisible = welcomeEvent.isLoading
                progressBar?.isVisible = welcomeEvent.isLoading
            }
            is WelcomeUiEvent.Logout -> {
                sharedViewModel.setLogin(null)
            }
        }
    }
}