package com.fukuni.mobiusdemo.login.domain

data class Email (val value: String) {
    val isValid: Boolean

    get() {
        val indexOfDot = value.lastIndexOf(".")
        val indexofAt = value.indexOf("@")
        return indexOfDot != -1 && indexofAt != -1 && indexOfDot > indexofAt
    }
}