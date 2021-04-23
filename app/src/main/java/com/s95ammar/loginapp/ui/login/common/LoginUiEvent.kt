package com.s95ammar.loginapp.ui.login.common

import androidx.annotation.StringRes

sealed class LoginUiEvent {
    class Loading(val isLoading: Boolean) : LoginUiEvent()
    class LoginError(@StringRes val errorStringId: Int) : LoginUiEvent()
}
