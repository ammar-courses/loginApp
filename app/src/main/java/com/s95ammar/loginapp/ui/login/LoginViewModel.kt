package com.s95ammar.loginapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.ui.login.common.LoginUiEvent
import com.s95ammar.loginapp.util.EventLiveData
import com.s95ammar.loginapp.util.EventMutableLiveData
import com.s95ammar.loginapp.util.call
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _login = MutableLiveData<String>()
    private val _uiEvent = EventMutableLiveData<LoginUiEvent>()

    val login: LiveData<String> = _login
    val uiEvent: EventLiveData<LoginUiEvent> = _uiEvent

    fun onLogin(login: String, password: String) {
        // launches a coroutine in the ViewModel and prevents possible leaks from the coroutine
        viewModelScope.launch {
            _uiEvent.call(LoginUiEvent.Loading(true))

            // Fake work to see the loading
            delay(2000) // can only be called inside a coroutine

            if (login == "Vlad" && password == "123456") {
                _login.value = login
            } else {
                _uiEvent.call(LoginUiEvent.LoginError(R.string.error_login_incorrect_credentials))
            }
            _uiEvent.call(LoginUiEvent.Loading(false))

        }

    }
}