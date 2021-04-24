package com.s95ammar.loginapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.fragments.isEmpty())
            navigateToLoginFragment()

        sharedViewModel.login.observe(this) { login ->
            if (login == null) navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment())
            .commit()
    }

}