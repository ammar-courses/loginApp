package com.s95ammar.loginapp.ui.welcome.common

sealed class WelcomeUiEvent {
    class Loading(val isLoading: Boolean) : WelcomeUiEvent()
    class Logout() : WelcomeUiEvent()
}
