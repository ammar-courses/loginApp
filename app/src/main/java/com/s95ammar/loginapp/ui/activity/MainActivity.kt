package com.s95ammar.loginapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.s95ammar.loginapp.databinding.ActivityMainBinding
import com.s95ammar.loginapp.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportFragmentManager.fragments.isEmpty())
            navigateToLoginFragment()

        sharedViewModel.login.observe(this) { login ->
            if (login == null) navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, LoginFragment())
            .commit()
    }

}