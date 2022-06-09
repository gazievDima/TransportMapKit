package com.gaziev.testapp.ui.common

sealed class StateUI {
    object Loading : StateUI()
    object Success : StateUI()
    object Error : StateUI()
}