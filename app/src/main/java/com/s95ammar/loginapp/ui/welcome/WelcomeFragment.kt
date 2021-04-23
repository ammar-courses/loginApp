package com.s95ammar.loginapp.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.ui.activity.SharedViewModel

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

        sharedViewModel.login.observe(viewLifecycleOwner) { login ->
            welcomeTextView.text = getString(R.string.format_welcome, login)
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
}