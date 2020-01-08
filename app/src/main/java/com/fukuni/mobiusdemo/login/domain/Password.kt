package com.fukuni.mobiusdemo.login.domain

data class Password(
    val value: String
) {

    val isValid : Boolean
    get() =
        value.length >= 8

}