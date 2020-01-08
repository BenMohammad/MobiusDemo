package com.fukuni.mobiusdemo.login

sealed class LoginEvent

data class EnterEmailEvent(
    val email: String
): LoginEvent()

data class EnterPasswordEvent(
    val password: String
): LoginEvent()