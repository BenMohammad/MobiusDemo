package com.fukuni.mobiusdemo.login

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import com.fukuni.mobiusdemo.login.domain.Email
import com.fukuni.mobiusdemo.login.domain.Password

data class LoginModel(val email: Option<Email>, val password: Option<Password>) {

    companion object{
        val BLANK = LoginModel(None, None)
    }

    fun enterEmail(email: String): LoginModel =

        copy(email = Some(Email(email)))


    fun enterPassword(password: String) : LoginModel =

        copy(password = Some(Password(password)))

}