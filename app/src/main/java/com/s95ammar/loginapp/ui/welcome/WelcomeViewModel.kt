package com.s95ammar.loginapp.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s95ammar.loginapp.ui.welcome.common.WelcomeUiEvent
import com.s95ammar.loginapp.util.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {

    private val _uiEvent = MutableLiveData<Event<WelcomeUiEvent>>()

    val uiEvent: LiveData<Event<WelcomeUiEvent>> = _uiEvent

    fun onLogout() = viewModelScope.launch {
        _uiEvent.value = Event(WelcomeUiEvent.Loading(true))
        delay(2000)
        _uiEvent.value = Event(WelcomeUiEvent.Loading(false))
        _uiEvent.value = Event(WelcomeUiEvent.Logout())
    }
}